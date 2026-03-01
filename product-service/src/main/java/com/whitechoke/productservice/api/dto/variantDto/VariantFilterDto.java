package com.whitechoke.productservice.api.dto.variantDto;

import lombok.Builder;

@Builder
public record VariantFilterDto(
        Long id,
        Integer size,
        Long productId,
        Integer pageSize,
        Integer pageNumber
) {
}
