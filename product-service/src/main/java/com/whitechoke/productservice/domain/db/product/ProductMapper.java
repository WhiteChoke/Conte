package com.whitechoke.productservice.domain.db.product;

import com.whitechoke.api.http.product.ProductResponseDto;
import com.whitechoke.productservice.api.dto.productDto.ProductCreateRequestDto;
import org.mapstruct.Mapper;

import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ProductMapper {
    ProductEntity toProductEntity(ProductCreateRequestDto request);
    ProductResponseDto toResponseDto(ProductEntity entity);
}
