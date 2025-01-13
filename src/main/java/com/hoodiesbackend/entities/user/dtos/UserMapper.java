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
                .build();
    }
}
