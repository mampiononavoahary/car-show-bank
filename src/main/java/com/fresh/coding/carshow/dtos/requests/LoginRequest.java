package com.fresh.coding.carshow.dtos.requests;


import jakarta.validation.constraints.*;

public record LoginRequest(
        @Email(message = "The email address must be valid.")
        @NotEmpty(message = "The email address cannot be empty.")
        String email,

        @NotBlank(message = "The password cannot be empty.")
        @Size(min = 5, message = "The password must be at least 5 characters long.")
        String password
) { }
