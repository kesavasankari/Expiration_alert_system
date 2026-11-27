package com.expiration_alert.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record UserRequest(
        @Email(message = "Enter a valid email address")
        @NotBlank(message = "Email cannot be empty")
        String email,
        @Size(min = 8, max = 16, message = "Should be in the range (8 to 16)")
        String password
) {
}
