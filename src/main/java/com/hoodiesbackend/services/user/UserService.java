package com.hoodiesbackend.services.user;

import com.hoodiesbackend.entities.LogIn;
import com.hoodiesbackend.entities.user.User;
import com.hoodiesbackend.entities.user.dtos.UserGetDto;
import com.hoodiesbackend.entities.user.dtos.UserMapper;
import com.hoodiesbackend.exceptions.BadRequestException;
import com.hoodiesbackend.exceptions.PasswordException;
import com.hoodiesbackend.exceptions.NotFoundException;
import com.hoodiesbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User entity) {

        String password = entity.getPassword();
        String confirmPassword = entity.getConfirmPassword();

        if (password.isBlank() || password.isEmpty()) {
            throw new PasswordException("Password of Confirm password id blank. You must fill them!");
        }
        if (!password.equals(confirmPassword)) {
            throw new PasswordException("Passwords don't match! Try again");
        }

        entity.setId(null);
        return userRepository.save(entity);
    }

    public UserGetDto read(Long id) {
        if (id < 0) {
            throw new BadRequestException("Id invalid!");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!"));

        return UserMapper.toUserGetDto(user);
    }

    public UserGetDto login(LogIn body) {

        System.out.println("valid body: " + body.isValid());
        if (!body.isValid()) {
            throw new BadRequestException("Email or password wrong!");
        }

        User user = userRepository.readUserByEmailAndPassword(body.getEmail(), body.getPassword())
                .orElseThrow(() -> new NotFoundException("This user doesn't exist!"));

        return UserMapper.toUserGetDto(user);
    }

    public User update(User entity) {
        return userRepository.save(entity);
    }

    public Boolean delete(Long id) {
        if (id <= 0) {
            throw new BadRequestException("Id is invalid!");
        }
        userRepository.deleteById(id);
        return true;
    }
}
