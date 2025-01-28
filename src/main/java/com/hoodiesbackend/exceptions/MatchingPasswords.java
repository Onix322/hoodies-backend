package com.hoodiesbackend.exceptions;

public class MatchingPasswords extends RuntimeException {
    public MatchingPasswords(String message) {
        super(message);
    }
}
