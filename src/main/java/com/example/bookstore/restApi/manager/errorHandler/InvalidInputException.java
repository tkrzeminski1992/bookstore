package com.example.bookstore.restApi.manager.errorHandler;

public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }
}
