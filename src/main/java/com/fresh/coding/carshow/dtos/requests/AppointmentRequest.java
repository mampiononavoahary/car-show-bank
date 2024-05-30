package com.fresh.coding.carshow.dtos.requests;

import jakarta.validation.constraints.*;
import java.time.Instant;

public record AppointmentRequest(
        @NotBlank String name,
        @NotBlank String firstName,
        @NotBlank @Email String email,
        @NotBlank String message,
        @NotBlank String contact,
        @FutureOrPresent Instant appointmentDate
) {}
