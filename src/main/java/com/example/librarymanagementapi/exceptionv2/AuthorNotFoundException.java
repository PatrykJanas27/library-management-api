package com.example.librarymanagementapi.exceptionv2;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long id) {
        super("Author with id " + id + " is not exists");
    }
}
