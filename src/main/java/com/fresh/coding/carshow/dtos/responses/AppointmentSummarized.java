package com.fresh.coding.carshow.dtos.responses;

import com.fresh.coding.carshow.enums.AppointmentStatus;

import java.time.Instant;

public record AppointmentSummarized(
        Long id,
        String name,
        String firstName,
        String email,
        String message,
        String contact,
        Instant appointmentDate,
        AppointmentStatus status,
        CarSummarized car
) {
}
