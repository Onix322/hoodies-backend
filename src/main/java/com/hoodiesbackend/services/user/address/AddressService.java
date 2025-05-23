package com.hoodiesbackend.services.user.address;

import com.hoodiesbackend.entities.user.address.Address;
import com.hoodiesbackend.entities.user.address.helpers.AddressDto;
import com.hoodiesbackend.entities.user.address.helpers.AddressMapper;
import com.hoodiesbackend.exceptions.BadRequestException;
import com.hoodiesbackend.exceptions.NotFoundException;
import com.hoodiesbackend.repositories.user.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressDto create(Address body){
        return AddressMapper.toDto(addressRepository.save(body));
    }

    public AddressDto update(Address body){
        System.out.println(body);
        return AddressMapper.toDto(addressRepository.save(body));
    }

    public List<AddressDto> getAllFor(Long userId){
        if(userId < 1) throw new BadRequestException("User id should be > 0");

        return addressRepository.findAddressByUserId(userId)
                .stream()
                .filter(Address::getActiveAddress)
                .map(AddressMapper::toDto)
                .toList();
    }

    public List<AddressDto> getAll(){
        return addressRepository.findAll()
                .stream()
                .map(AddressMapper::toDto)
                .toList();
    }

    public AddressDto getAddress(Long addressId){
        if(addressId < 1) throw new BadRequestException("User id should be > 0");

        return AddressMapper.toDto(
                addressRepository.findById(addressId)
                        .orElseThrow(() -> new NotFoundException("Address not found!"))
        );
    }

    public AddressDto deleteAddress(Long addressId){
        if(addressId < 1) throw new BadRequestException("User id should be > 0");

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not found!"));

        address.setActiveAddress(false);
        this.update(address);

        return AddressMapper.toDto(address);
    }
}
