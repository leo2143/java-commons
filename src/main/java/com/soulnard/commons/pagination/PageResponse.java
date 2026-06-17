package com.soulnard.commons.pagination;

import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Metadata de paginación para respuestas API.
 */
public record PageResponse(
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last,
        List<SortInfo> sort
) {

    public record SortInfo(String property, Sort.Direction direction) {
    }

    public static PageResponse from(org.springframework.data.domain.Page<?> springPage) {
        var sortInfo = springPage.getSort().stream()
                .map(order -> new SortInfo(order.getProperty(), order.getDirection()))
                .toList();
        return new PageResponse(
                springPage.getNumber(),
                springPage.getSize(),
                springPage.getTotalElements(),
                springPage.getTotalPages(),
                springPage.isFirst(),
                springPage.isLast(),
                sortInfo
        );
    }
}
