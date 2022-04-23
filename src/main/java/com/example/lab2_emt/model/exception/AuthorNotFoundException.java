package com.example.lab2_emt.model.exception;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long id) {
        super(String.format("Author with id %d was not found",id));
    }
}
