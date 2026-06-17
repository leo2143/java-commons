package com.soulnard.commons.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Request de paginación que se puede recibir desde el cliente
 * y convertir a un Pageable de Spring Data.
 */
public record PageableRequest(
        int page,
        int size,
        String sortBy,
        Sort.Direction direction
) {

    public PageableRequest {
        if (page < 0) page = 0;
        if (size <= 0) size = 20;
        if (size > 100) size = 100;
        if (direction == null) direction = Sort.Direction.ASC;
    }

    public static PageableRequest of(int page, int size) {
        return new PageableRequest(page, size, null, Sort.Direction.ASC);
    }

    public Pageable toPageable() {
        if (sortBy != null && !sortBy.isBlank()) {
            return PageRequest.of(page, size, Sort.by(direction, sortBy));
        }
        return PageRequest.of(page, size);
    }
}
