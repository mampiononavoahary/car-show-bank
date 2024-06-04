package com.fresh.coding.carshow.services.impl;

import com.fresh.coding.carshow.dtos.requests.AppointmentRequest;
import com.fresh.coding.carshow.dtos.responses.AppointmentSummarized;
import com.fresh.coding.carshow.enums.AppointmentStatus;
import com.fresh.coding.carshow.exceptions.NotFoundException;
import com.fresh.coding.carshow.mappers.AppointmentMapper;
import com.fresh.coding.carshow.repositories.AppointmentRepository;
import com.fresh.coding.carshow.repositories.CarRepository;
import com.fresh.coding.carshow.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final CarRepository carRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public AppointmentSummarized createAppointment(AppointmentRequest appointmentRequest) {
        var car = carRepository.findById(appointmentRequest.carId()).orElseThrow(() -> new NotFoundException("Car not found"));
        var appointment = appointmentMapper.toEntity(appointmentRequest);
        appointment.setCar(car);
        var savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toResponse(savedAppointment);
    }


    @Override
    public AppointmentSummarized findAppointment(Long id) {
        var foundAppointment = appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Appointment not found"));
        return appointmentMapper.toResponse(foundAppointment);
    }

    @Override
    public AppointmentSummarized updateStatusAppointment(Long id, String status) {
        var foundAppointment = appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Appointment not found"));
        var newStatus = AppointmentStatus.valueOf(status);
        foundAppointment.setStatus(newStatus);
        var savedAppointment = appointmentRepository.save(foundAppointment);
        return appointmentMapper.toResponse(savedAppointment);
    }

    @Override
    public List<AppointmentSummarized> findAllAppointments() {
        return appointmentRepository.findAll()
                .stream().map(appointmentMapper::toResponse)
                .collect(Collectors.toList());
    }
}
