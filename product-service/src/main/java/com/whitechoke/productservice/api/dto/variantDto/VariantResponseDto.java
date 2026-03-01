package com.whitechoke.productservice.api.dto.variantDto;

import com.whitechoke.productservice.api.dto.productDto.ProductResponseDto;
import com.whitechoke.productservice.domain.db.variant.Size;

public record VariantResponseDto(
        Long id,
        Size size,
        Integer priceModifier,
        ProductResponseDto product
) {
}
