package com.hoodiesbackend.services.user;

import com.hoodiesbackend.entities.user.helpers.PasswordChange;
import com.hoodiesbackend.entities.user.User;
import com.hoodiesbackend.exceptions.MatchingPasswords;
import com.hoodiesbackend.exceptions.NotFoundException;
import com.hoodiesbackend.exceptions.PasswordException;
import com.hoodiesbackend.repositories.user.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
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

        String encryptedPass = BCrypt.hashpw(body.getNewPassword(), BCrypt.gensalt());

        if (user.getPassword().equals(encryptedPass)) {
            throw new MatchingPasswords("Old password can't match the new one!");
        }
        user.setPassword(encryptedPass);
        user.setConfirmPassword(encryptedPass);
        userRepository.save(user);

        return true;

    }
}
