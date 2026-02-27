package com.whitechoke.productservice.api.dto;

import com.whitechoke.productservice.domain.db.ProductType;
import lombok.Builder;

@Builder
public record ProductFilterDto(
        Long id,
        ProductType productType,
        Boolean isAvailable,
        Integer pageSize,
        Integer pageNumber
) {
}
