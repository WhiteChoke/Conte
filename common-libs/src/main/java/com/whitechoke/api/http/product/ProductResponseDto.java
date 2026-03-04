package com.whitechoke.api.http.product;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponseDto(
        Long id,
        ProductType productType,
        String name,
        BigDecimal basePrice,
        String description,
        Boolean isAvailable,
        List<Long> variantsId
) implements ProductDto {
}
