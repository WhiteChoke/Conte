package com.whitechoke.productservice.api.dto.variantDto;

import com.whitechoke.api.http.variant.VariantResponseDto;
import lombok.Builder;

import java.util.List;

@Builder
public record VariantFilterResponseDto(
        Integer totalPages,
        Long totalElements,
        List<VariantResponseDto> variantResponse
) {
}
