package com.whitechoke.productservice.api.dto.variantDto;

import lombok.Builder;

@Builder
public record VariantFilterDto(
        Long id,
        Integer size,
        Integer productId,
        Integer pageSize,
        Integer pageNumber
) {
}
