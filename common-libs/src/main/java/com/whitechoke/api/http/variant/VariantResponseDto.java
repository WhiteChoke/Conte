package com.whitechoke.api.http.variant;

import lombok.Builder;

@Builder
public record VariantResponseDto(
        Long id,
        Integer size,
        Integer priceModifier,
        Long productId
) {
}
