package com.fresh.coding.carshow.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

public record AppointmentRequest(
        @NotBlank String name,
        @NotBlank String firstName,
        @NotBlank @Email String email,
        @NotBlank String message,
        @NotBlank String contact,
        @FutureOrPresent Instant appointmentDate
) {
}
