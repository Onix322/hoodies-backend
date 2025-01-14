package com.hoodiesbackend.repositories;

import com.hoodiesbackend.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> readUserByEmailAndPassword(String email, String password);
}
