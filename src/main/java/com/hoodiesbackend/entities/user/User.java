package com.hoodiesbackend.entities.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name="email", nullable = false)
    @NotBlank
    @Email
    String email;

    @Column(name="password", nullable = false)
    @NotBlank
    String password;

    @Column(name="phone", nullable = false)
    @NotBlank
    String phone;

    @Column(name="name", nullable = false)
    @NotBlank
    String name;

    @Column(name="role", nullable = false)
    @NotBlank
    Role role;

}
