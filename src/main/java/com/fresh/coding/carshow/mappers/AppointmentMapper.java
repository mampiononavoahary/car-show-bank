package com.fresh.coding.carshow.mappers;

import com.fresh.coding.carshow.dtos.requests.AppointmentRequest;
import com.fresh.coding.carshow.dtos.responses.AppointmentSummarized;
import com.fresh.coding.carshow.entities.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentMapper {
    private final CarMapper carMapper;

    public Appointment toEntity(AppointmentRequest appointmentRequest) {
        return Appointment.builder()
                .id(appointmentRequest.id())
                .name(appointmentRequest.name())
                .firstName(appointmentRequest.firstName())
                .email(appointmentRequest.email())
                .status(appointmentRequest.status())
                .contact(appointmentRequest.contact())
                .message(appointmentRequest.message())
                .appointmentDate(appointmentRequest.appointmentDate())
                .build();
    }


    public AppointmentSummarized toResponse(Appointment appointment){
        return new AppointmentSummarized(
                appointment.getId(),
                appointment.getName(),
                appointment.getFirstName(),
                appointment.getEmail(),
                appointment.getMessage(),
                appointment.getContact(),
                appointment.getAppointmentDate(),
                appointment.getStatus(),
                carMapper.toResponse(appointment.getCar())
        );
    }

}
