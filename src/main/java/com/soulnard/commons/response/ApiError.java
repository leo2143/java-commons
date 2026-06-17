package com.soulnard.commons.response;

import java.time.Instant;
import java.util.Map;

public record ApiError(
        boolean success,
        int status,
        String error,
        String message,
        String path,
        Instant timestamp,
        Map<String, String> validationErrors
) {

    public static ApiError of(int status, String error, String message, String path) {
        return new ApiError(false, status, error, message, path, Instant.now(), null);
    }

    public static ApiError withValidation(int status, String path, Map<String, String> errors) {
        return new ApiError(false, status, "Validation Failed",
                "Request validation failed", path, Instant.now(), errors);
    }

    public static ApiError notFound(String message, String path) {
        return of(404, "Not Found", message, path);
    }

    public static ApiError badRequest(String message, String path) {
        return of(400, "Bad Request", message, path);
    }

    public static ApiError internal(String message, String path) {
        return of(500, "Internal Server Error", message, path);
    }
}
