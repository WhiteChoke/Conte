package com.whitechoke.productservice.api.dto.variantDto;

import com.whitechoke.productservice.api.dto.productDto.ProductResponseDto;

public record VariantCreateRequestDto(
        Integer size,
        Integer priceModifier,
        ProductResponseDto product
) {
}
