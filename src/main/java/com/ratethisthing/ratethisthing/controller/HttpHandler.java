package com.ratethisthing.ratethisthing.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpHandler {

    // TODO
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
//        // Customize the response body as needed
//        ErrorResponse errorResponse = new ErrorResponse(
//                "User already exists.",
//                ex.getMessage() // Include exception message for more context (optional)
//        );
//
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse); // Return 409 Conflict
//    }
}
