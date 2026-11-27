package com.expiration_alert.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @NotBlank
    private String email;
    @Length(min = 8,max = 16)
    @NotBlank
    private String password;
    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List <Product> products = new ArrayList<>();
}
