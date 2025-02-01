package com.hoodiesbackend.entities.user;

import com.hoodiesbackend.entities.user.helpers.ActivationStatus;
import com.hoodiesbackend.entities.user.helpers.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Email mandatory!")
    @Email(message = "Enter a valid email please!")
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "You may want to enter a password!")
    @Size(message = "The size counts... minimum 4 characters", min = 4)
    private String password;

    @Column(name = "confirm_password", nullable = false)
    @Transient
    @NotBlank(message = "You also need to confirm the password...")
    private String confirmPassword;

    @Column(name = "phone", nullable = false)
    @NotBlank(message = "What if we need to get in touch? Don't you think? Enter the phone number...")
    private String phone;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "You don't have a name??? Sad.. ")
    private String name;

    @Column(name = "role", nullable = false)
    @NotNull(message = "Role mandatory!")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "activation_status", nullable = false)
    @NotNull(message = "Activation Status mandatory!")
    @Enumerated(EnumType.STRING)
    private ActivationStatus activationStatus;

    @Column(name = "userImage")
    private String userImage;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public @NotNull(message = "Activation Status mandatory!") ActivationStatus getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(@NotNull(message = "Activation Status mandatory!") ActivationStatus activationStatus) {
        this.activationStatus = activationStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
