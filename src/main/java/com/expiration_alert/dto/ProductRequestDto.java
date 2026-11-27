package com.expiration_alert.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;


public record ProductRequestDto(

        @Size(min = 3,max = 50)
        String name,
       @Min(1)
       @Max(999)
        int quantity,
        LocalDate expiry_date
) {
}
