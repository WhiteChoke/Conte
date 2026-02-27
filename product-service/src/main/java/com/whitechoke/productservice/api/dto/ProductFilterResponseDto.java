package com.whitechoke.productservice.api.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductFilterResponseDto(
        Integer totalPages,
        Long totalElements,
        List<ProductResponseDto> productResponse
) {
}
