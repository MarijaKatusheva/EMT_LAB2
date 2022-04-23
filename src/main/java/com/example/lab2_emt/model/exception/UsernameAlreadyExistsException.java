package com.example.lab2_emt.model.exception;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String message) {
        super(String.format("User with Username %s already exists",message));
    }
}
