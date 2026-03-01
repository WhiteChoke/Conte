package com.whitechoke.productservice.api.dto.variantDto;

public record VariantCreateRequestDto(
        Integer size,
        Integer priceModifier,
        Long productId
) {
}
