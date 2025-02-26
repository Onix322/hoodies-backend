package com.hoodiesbackend.repositories.user;

import com.hoodiesbackend.entities.user.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAddressByUserId(Long userId);

    Boolean deleteAddressByUserId(Long userId);
}
