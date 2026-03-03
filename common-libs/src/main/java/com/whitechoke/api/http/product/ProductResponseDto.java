package com.whitechoke.api.http.product;

import com.whitechoke.api.http.variant.VariantResponseDto;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponseDto(
        Long id,
        ProductType productType,
        String name,
        BigDecimal basePrice,
        String description,
        Boolean isAvailable,
        List<VariantResponseDto> variants
) implements ProductDto {
}
