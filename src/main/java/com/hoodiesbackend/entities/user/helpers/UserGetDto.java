package com.hoodiesbackend.entities.user.helpers;

import lombok.Data;

@Data
public class UserGetDto {

    private Long id;
    private String email;
    private String phone;
    private String name;
    private Role role;
    private String userImage;
    private ActivationStatus activationStatus;

    private UserGetDto(BuilderGetDto builderGetDto){
        this.id = builderGetDto.id;
        this.name= builderGetDto.name;
        this.email = builderGetDto.email;
        this.phone = builderGetDto.phone;
        this.role = builderGetDto.role;
        this.userImage = builderGetDto.userImage;
        this.activationStatus = builderGetDto.activationStatus;
    }

    public UserGetDto(){}

    public static class BuilderGetDto{
        private Long id = 0L;
        private String email ="UNKNOWN";
        private String phone ="UNKNOWN";
        private String name ="UNKNOWN";
        private Role role = Role.CUSTOMER;
        private String userImage = "UNKNOWN";
        private ActivationStatus activationStatus = ActivationStatus.ACTIVATED;

        public BuilderGetDto() {
        }

        public BuilderGetDto setId(Long id) {
            this.id = id;
            return this;
        }

        public BuilderGetDto setActivationStatus(ActivationStatus activationStatus) {
            this.activationStatus = activationStatus;
            return this;
        }

        public BuilderGetDto setRole(Role role) {
            this.role = role;
            return this;
        }

        public BuilderGetDto setName(String name) {
            this.name = name;
            return this;
        }

        public BuilderGetDto setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public BuilderGetDto setEmail(String email) {
            this.email = email;
            return this;
        }

        public BuilderGetDto setUserImage(String userImage) {
            this.userImage = userImage;
            return this;
        }

        public UserGetDto build(){
            return new UserGetDto(this);
        }
    }
}
