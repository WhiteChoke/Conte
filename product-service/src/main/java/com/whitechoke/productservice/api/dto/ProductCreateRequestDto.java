package com.whitechoke.productservice.api.dto;

import com.whitechoke.productservice.domain.db.ProductType;

import java.math.BigDecimal;

public record ProductCreateRequestDto(
        ProductType productType,
        String name,
        BigDecimal basePrice,
        String description
) implements ProductDto {
}
