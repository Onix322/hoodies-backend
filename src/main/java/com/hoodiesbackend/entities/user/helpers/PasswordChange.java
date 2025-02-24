package com.hoodiesbackend.entities.user.helpers;

import lombok.Data;

@Data
public class PasswordChange {
    Long userId;
    String oldPassword;
    String newPassword;
    String confirmNewPassword;

    public PasswordChange(Long userId, String oldPassword, String newPassword, String confirmNewPassword) {
        this.userId = userId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
    }
}
