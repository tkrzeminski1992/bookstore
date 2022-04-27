package com.example.bookstore.restApi.api;

import com.example.bookstore.restApi.manager.errorHandler.InvalidInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidInputExceptionControllerAdvice {

    @ExceptionHandler(InvalidInputException.class)
    ResponseEntity<String> handleIllegalArgument(InvalidInputException ex) {
        return ResponseEntity.status(422).body(ex.getMessage());
    }
}
