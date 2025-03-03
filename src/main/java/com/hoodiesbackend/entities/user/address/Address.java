package com.hoodiesbackend.entities.user.address;

import com.hoodiesbackend.entities.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull(message = "User mandatory!")
    private User user;

    @Column(name = "country", nullable = false)
    @NotNull(message = "Country mandatory!")
    private String country;

    @Column(name = "city", nullable = false)
    @NotNull(message = "City mandatory!")
    private String city;

    @Column(name = "state", nullable = false)
    @NotNull(message = "State mandatory!")
    private String state;

    @Column(name = "street", nullable = false)
    @NotNull(message = "Street mandatory!")
    private String street;

    @Column(name = "number", nullable = false)
    @NotNull(message = "Number mandatory!")
    private String number;

    @Column(name = "zip", nullable = false)
    @NotNull(message = "Zipcode mandatory!")
    private String zip;

    @Column(name = "active_address", nullable = false)
    @ColumnDefault(value = "true")
    @NotNull(message = "ActiveAddress mandatory!")
    private Boolean activeAddress;

    @Column(name = "main_address", nullable = false)
    @ColumnDefault(value = "false")
    @NotNull(message = "MainAddress mandatory!")
    private Boolean mainAddress;
}
