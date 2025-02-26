package com.hoodiesbackend.entities.user.address.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddressDto {

    private Long id;

    private String country;

    private String city;

    private String state;

    private String street;

    private String number;

    private String zip;
}
