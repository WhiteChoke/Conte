package com.whitechoke.productservice.api.dto;

import java.math.BigDecimal;

public record ProductUpdateRequestDto(
        String name,
        BigDecimal basePrice,
        String description
) {
}
