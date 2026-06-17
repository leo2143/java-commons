package com.soulnard.commons.exception;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String entityName, Object id) {
        super("%s not found with id: %s".formatted(entityName, id), 404);
    }

    public EntityNotFoundException(String message) {
        super(message, 404);
    }
}
