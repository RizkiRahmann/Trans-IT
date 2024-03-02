package com.pioneers.transit.controller;

import com.pioneers.transit.dto.response.ControllerResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ControllerResponse<String>> constraintViolationException(ConstraintViolationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ControllerResponse.<String>builder().message(exception.getMessage()).build());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ControllerResponse<String>> apiException(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(ControllerResponse.<String>builder().message(exception.getReason()).build());
    }

}
