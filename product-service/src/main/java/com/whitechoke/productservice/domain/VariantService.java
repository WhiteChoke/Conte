package com.whitechoke.productservice.domain;

import com.whitechoke.productservice.api.dto.variantDto.VariantCreateRequestDto;
import com.whitechoke.productservice.api.dto.variantDto.VariantFilterDto;
import com.whitechoke.productservice.api.dto.variantDto.VariantFilterResponseDto;
import com.whitechoke.productservice.api.dto.variantDto.VariantResponseDto;

public interface VariantService {
    VariantFilterResponseDto getVariantByFilter(VariantFilterDto filter);
    VariantResponseDto createVariant(VariantCreateRequestDto request);
    void deleteVariantById(Long id);
}
