package com.whitechoke.productservice.api.dto;

import com.whitechoke.productservice.domain.db.ProductType;

import java.math.BigDecimal;

public record ProductRequestDto(
        ProductType productType,
        String name,
        BigDecimal basePrice,
        String description
) {
}
