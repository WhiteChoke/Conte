package com.whitechoke.productservice.api.dto.productDto;

import com.whitechoke.api.http.product.ProductResponseDto;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductFilterResponseDto(
        Integer totalPages,
        Long totalElements,
        List<ProductResponseDto> productResponse
) {
}
