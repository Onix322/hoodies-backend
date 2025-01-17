package com.hoodiesbackend.entities.login;

public class LogIn {
    private String email;
    private String password;

    public LogIn(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isValid(){

        System.out.println(this);
        return !(email.isBlank() || email.isEmpty()) &&
                !(password.isBlank() || password.isEmpty());
    }

    @Override
    public String toString() {
        return "LogIn{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
