package com.lms.exam_registration.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data // Generates getters, setters, toString, equals, and hashCode
@AllArgsConstructor // Generates a constructor with all fields as parameters
public class ErrorDetails {
    private LocalDateTime timestamp;
    private int status;
    private String message;
}