package com.whitechoke.api.http.variant;

import com.whitechoke.api.http.product.ProductResponseDto;
import lombok.Builder;

@Builder
public record VariantResponseDto(
        Long id,
        Integer size,
        Integer priceModifier,
        ProductResponseDto product
) {
}
