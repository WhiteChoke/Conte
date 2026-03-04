package com.whitechoke.productservice.domain;

import com.whitechoke.productservice.api.dto.variantDto.VariantCreateRequestDto;
import com.whitechoke.productservice.api.dto.variantDto.VariantFilterDto;
import com.whitechoke.productservice.api.dto.variantDto.VariantFilterResponseDto;
import com.whitechoke.api.http.variant.VariantResponseDto;

import java.util.List;

public interface VariantService {
    VariantFilterResponseDto getVariantByFilter(VariantFilterDto filter);
    VariantResponseDto createVariant(VariantCreateRequestDto request);
    void deleteVariantById(Long id);
    List<VariantResponseDto> getVariantsByIds(List<Long> ids);
}
