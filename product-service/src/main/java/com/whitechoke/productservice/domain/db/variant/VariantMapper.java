package com.whitechoke.productservice.domain.db.variant;

import com.whitechoke.productservice.api.dto.variantDto.VariantCreateRequestDto;
import com.whitechoke.api.http.variant.VariantResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface VariantMapper {
    // TODO: Implement linking product with entity by product id
    VariantEntity toEntity(VariantCreateRequestDto request);
    VariantResponseDto toResponseDto(VariantEntity entity);
}
