package com.whitechoke.productservice.api.dto.productDto;

import com.whitechoke.productservice.domain.db.product.ProductType;

import java.math.BigDecimal;

public record ProductResponseDto(
        Long id,
        ProductType productType,
        String name,
        BigDecimal basePrice,
        String description,
        Boolean isAvailable
) implements ProductDto {
}
