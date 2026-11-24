package com.expirtion_alert.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @NotNull
    @NotBlank
    private String email;
    @Length(min = 8,max = 16)
    @NotNull
    @NotBlank
    private String password;
    @OneToMany(fetch = FetchType.LAZY)
    private List <Product> products;
}
