package com.hoodiesbackend.entities.user.address.helpers;

import com.hoodiesbackend.entities.user.address.Address;

public class AddressMapper {
    public static AddressDto toDto(Address address){
        return AddressDto.builder()
                .city(address.getCity())
                .id(address.getId())
                .country(address.getCountry())
                .number(address.getNumber())
                .state(address.getState())
                .street(address.getStreet())
                .zip(address.getZip())
                .build();
    }
}
