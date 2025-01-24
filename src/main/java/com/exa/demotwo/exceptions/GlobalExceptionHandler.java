package com.exa.demotwo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

/**
 * The GlobalExceptionHandler class is a centralized exception handler for the application.
 * It uses Spring's @ControllerAdvice to handle exceptions thrown by controllers
 * and provide meaningful error responses to the client.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation exceptions that occur when a method argument fails validation.
     *
     * @param ex the MethodArgumentNotValidException that was thrown
     * @return a ResponseEntity containing a map of validation errors and an HTTP status of 400 Bad Request
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions that occur when the HTTP message is not readable.
     *
     * @param ex the HttpMessageNotReadableException that was thrown
     * @return a ResponseEntity containing an error message and an HTTP status of 400 Bad Request
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("Invalid input: Please provide a numeric ID.");
    }
    
    /**
     * Handles exceptions that occur when there is a type mismatch in method arguments.
     *
     * @param ex the MethodArgumentTypeMismatchException that was thrown
     * @return a ResponseEntity containing an error message and an HTTP status of 400 Bad Request
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().body("Invalid input: Please provide a numeric ID.");
    }

}
