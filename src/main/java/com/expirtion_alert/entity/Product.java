package com.expirtion_alert.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.time.LocalDate;
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @Length(min = 1 , max = 999)
    private int quantity;
    @NotNull
    @NotBlank
    private LocalDate expiry_date;
    @CreationTimestamp
    @Column(updatable = false)
    private Instant created_at;
    @UpdateTimestamp

    private Instant updated_at;
}
