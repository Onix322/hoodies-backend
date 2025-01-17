package com.hoodiesbackend.entities.user.dtos;

import com.hoodiesbackend.entities.user.Role;

public class UserGetDto {

    private Long id;
    private String email;
    private String phone;
    private String name;
    private Role role;
    private String userImage;

    private UserGetDto(BuilderGetDto builderGetDto){
        this.id = builderGetDto.id;
        this.name= builderGetDto.name;
        this.email = builderGetDto.email;
        this.phone = builderGetDto.phone;
        this.role = builderGetDto.role;
        this.userImage = builderGetDto.userImage;
    }

    public UserGetDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserGetDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    static class BuilderGetDto{
        private Long id = 0L;
        private String email ="UNKNOWN";
        private String phone ="UNKNOWN";
        private String name ="UNKNOWN";
        private Role role = Role.CUSTOMER;
        private String userImage = "UNKNOWN";

        public BuilderGetDto() {
        }

        public BuilderGetDto setId(Long id) {
            this.id = id;
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
