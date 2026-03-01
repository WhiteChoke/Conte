package com.whitechoke.productservice.api.dto.variantDto;

import com.whitechoke.productservice.api.dto.productDto.ProductResponseDto;

public record VariantResponseDto(
        Long id,
        Integer size,
        Integer priceModifier,
        ProductResponseDto product
) {
}
