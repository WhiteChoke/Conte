package com.whitechoke.productservice.api.dto.variantDto;

import lombok.Builder;

@Builder
public record VariantResponseDto(
        Long id,
        Integer size,
        Integer priceModifier,
        Long productId
) {
}
