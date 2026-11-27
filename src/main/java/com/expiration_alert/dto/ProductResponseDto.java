package com.expiration_alert.dto;


import lombok.Data;

import java.time.LocalDate;


public record ProductResponseDto(
        Long id,
        String name,
        int quantity,
        LocalDate expiry_data
) {
}
