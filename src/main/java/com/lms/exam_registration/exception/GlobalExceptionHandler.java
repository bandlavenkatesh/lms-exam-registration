package com.lms.exam_registration.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    //Handle resource not found exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(ResourceNotFoundException ex){
        
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(BadRequestException ex){
        
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGenericException(ResourceNotFoundException ex){
        
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occured");
    }

    private ResponseEntity<ErrorDetails> buildErrorResponse(HttpStatus status, String message) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), status.value(), message);
        return new ResponseEntity<>(errorDetails, status);
    }
}
