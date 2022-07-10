package com.example.librarymanagementapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }
}
