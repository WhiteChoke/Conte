package com.whitechoke.productservice.api.dto.productDto;

import com.whitechoke.productservice.domain.db.product.ProductType;
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
