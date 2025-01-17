package com.hoodiesbackend.entities.user.dtos;

import com.hoodiesbackend.entities.user.User;

public class UserMapper {

    public static UserGetDto toUserGetDto(User user){
        return new UserGetDto.BuilderGetDto()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setName(user.getName())
                .setPhone(user.getPhone())
                .setRole(user.getRole())
                .setUserImage(user.getUserImage())
                .build();
    }

    public static User toUserWith(UserGetDto userDto, User user){

        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setConfirmPassword(user.getPassword());
        user.setPhone(userDto.getPhone());
        user.setRole(userDto.getRole());
        user.setUserImage(userDto.getUserImage());

        return user;
    }
}
