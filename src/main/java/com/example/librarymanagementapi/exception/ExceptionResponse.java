package com.example.librarymanagementapi.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private final LocalDateTime localDateTime;
    private final HttpStatus httpStatus;
    private final String message;

    public ExceptionResponse(HttpStatus httpStatus, String message) {
        this.localDateTime = LocalDateTime.now();
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
