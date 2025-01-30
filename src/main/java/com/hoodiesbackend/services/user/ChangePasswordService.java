package com.hoodiesbackend.services.user;

import com.hoodiesbackend.entities.user.passwordChange.PasswordChange;
import com.hoodiesbackend.entities.user.User;
import com.hoodiesbackend.exceptions.MatchingPasswords;
import com.hoodiesbackend.exceptions.NotFoundException;
import com.hoodiesbackend.exceptions.PasswordException;
import com.hoodiesbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {

    private final UserRepository userRepository;

    public ChangePasswordService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean change(PasswordChange body) {

        User user = userRepository.findById(body.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!body.getNewPassword().equals(body.getConfirmNewPassword())) {
            throw new PasswordException("Confirm password should be the same with New password");
        }

        if (user.getPassword().equals(body.getNewPassword())) {
            throw new MatchingPasswords("Old password can't match the new one!");
        }

        user.setPassword(body.getNewPassword());
        user.setConfirmPassword(body.getConfirmNewPassword());
        userRepository.save(user);

        return true;

    }
}
