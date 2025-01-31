package com.hoodiesbackend.repositories;

import com.hoodiesbackend.entities.user.ActivationStatus;
import com.hoodiesbackend.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query(value = "update User u set u.activationStatus = :activationStatus where u.id = :id")
    Integer setActivationStatus(@Param("id") Long id,@Param("activationStatus")  ActivationStatus activationStatus);

    Optional<User> readUserByEmail(String email);
}
