package com.soulnard.commons.exception;

import java.util.Map;

import lombok.Getter;

@Getter
public class ValidationException extends BusinessException {

    private final Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        super("Request validation failed", 400);
        this.errors = Map.copyOf(errors);
    }

    public ValidationException(String field, String message) {
        super("Validation error: %s".formatted(message), 400);
        this.errors = Map.of(field, message);
    }
}
