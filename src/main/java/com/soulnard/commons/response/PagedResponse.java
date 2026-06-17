package com.soulnard.commons.response;

import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.List;

public record PagedResponse<T>(
        boolean success,
        List<T> data,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last,
        Instant timestamp
) {

    public static <T> PagedResponse<T> from(Page<T> springPage) {
        return new PagedResponse<>(
                true,
                springPage.getContent(),
                springPage.getNumber(),
                springPage.getSize(),
                springPage.getTotalElements(),
                springPage.getTotalPages(),
                springPage.isFirst(),
                springPage.isLast(),
                Instant.now()
        );
    }

    public static <T> PagedResponse<T> from(Page<?> springPage, List<T> mappedContent) {
        return new PagedResponse<>(
                true,
                mappedContent,
                springPage.getNumber(),
                springPage.getSize(),
                springPage.getTotalElements(),
                springPage.getTotalPages(),
                springPage.isFirst(),
                springPage.isLast(),
                Instant.now()
        );
    }
}
