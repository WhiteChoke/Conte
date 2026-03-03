package com.whitechoke.productservice.api.dto.productDto;

import com.whitechoke.api.http.product.ProductDto;

import java.math.BigDecimal;

public record ProductUpdateRequestDto(
        String name,
        BigDecimal basePrice,
        String description
) implements ProductDto {
}
