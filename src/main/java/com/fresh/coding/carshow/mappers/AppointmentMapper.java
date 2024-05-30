package com.fresh.coding.carshow.mappers;

import com.fresh.coding.carshow.dtos.requests.AppointmentRequest;
import com.fresh.coding.carshow.dtos.responses.AppointmentResponse;
import com.fresh.coding.carshow.entities.Appointment;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class AppointmentMapper {
    public Appointment toEntity(AppointmentRequest requestDTO) {
        return new Appointment()
                .setName(requestDTO.name())
                .setFirstName(requestDTO.firstName())
                .setEmail(requestDTO.email())
                .setMessage(requestDTO.message())
                .setContact(requestDTO.contact())
                .setAppointmentDate(requestDTO.appointmentDate());
    }

    public AppointmentResponse toResponse(Appointment appointment) {
        return new AppointmentResponse(
                appointment.getId(),
                appointment.getName(),
                appointment.getFirstName(),
                appointment.getEmail(),
                appointment.getMessage(),
                appointment.getContact(),
                this.formatInstant(appointment.getAppointmentDate()),
                appointment.getStatus()
        );
    }

    public  String formatInstant(Instant instant) {
        var localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
