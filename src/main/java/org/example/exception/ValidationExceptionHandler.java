package org.example.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
@Order(1)
public class ValidationExceptionHandler {
    // Handler cho @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                fieldErrors.put(err.getField(), err.getDefaultMessage()));

        String path = request.getRequestURI();

        return buildErrorResponse("Validation Failed", fieldErrors, HttpStatus.BAD_REQUEST, path);
    }

    // Handler cho @RequestParam / @PathVariable
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {
        Map<String, String> violations = new HashMap<>();
        ex.getConstraintViolations().forEach(v -> {
            String field = v.getPropertyPath().toString();
            field = field.substring(field.lastIndexOf('.') + 1); // rút gọn
            violations.put(field, v.getMessage());
        });

        String path = request.getRequestURI();

        return buildErrorResponse("Validation Failed", violations, HttpStatus.BAD_REQUEST, path);
    }

    private Map<String, Object> buildErrorResponse(String message, Map<String, String> errors, HttpStatus status, String path) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", ZonedDateTime.now());
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", message);
        response.put("validationErrors", errors);
        response.put("path", path);
        return response;
    }
}
