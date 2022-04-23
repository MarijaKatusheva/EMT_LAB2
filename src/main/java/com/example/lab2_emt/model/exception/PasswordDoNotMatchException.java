package com.example.lab2_emt.model.exception;

public class PasswordDoNotMatchException extends RuntimeException{
    public PasswordDoNotMatchException() {
        super("Password do not match exception");
    }
}
