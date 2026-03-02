package com.whitechoke.productservice.api.dto.productDto;

import com.whitechoke.productservice.api.dto.variantDto.VariantResponseDto;
import com.whitechoke.productservice.domain.db.product.ProductType;

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
