package com.whitechoke.productservice.domain.impl;

import com.whitechoke.productservice.api.dto.variantDto.VariantCreateRequestDto;
import com.whitechoke.productservice.api.dto.variantDto.VariantFilterDto;
import com.whitechoke.productservice.api.dto.variantDto.VariantFilterResponseDto;
import com.whitechoke.productservice.api.dto.variantDto.VariantResponseDto;
import com.whitechoke.productservice.domain.VariantService;
import com.whitechoke.productservice.domain.db.product.ProductRepository;
import com.whitechoke.productservice.domain.db.variant.VariantMapper;
import com.whitechoke.productservice.domain.db.variant.VariantsRepository;
import com.whitechoke.productservice.domain.validation.VariantValidate;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VariantServiceImpl implements VariantService {

    private final VariantsRepository repository;
    private final VariantMapper mapper;
    private final VariantValidate variantValidate;
    private final ProductRepository productRepository;

    @Override
    public VariantFilterResponseDto getVariantByFilter(VariantFilterDto filter) {

        var pageNumber = filter.pageNumber() != null
                ? filter.pageNumber()
                : 0;

        var pageSize = filter.pageSize() != null
                ? filter.pageSize()
                : 5;

        var pageable = Pageable.ofSize(pageSize).withPage(pageNumber);

        var found = repository.getVariantByFilter(
                filter.id(),
                filter.size(),
                filter.productId(),
                pageable
        );

        var variantDtoList = found.stream().map(mapper::toResponseDto).toList();

        return VariantFilterResponseDto
                .builder()
                .totalPages(found.getTotalPages())
                .totalElements(found.getTotalElements())
                .variantResponse(variantDtoList)
                .build();
    }

    @Override
    @Transactional
    public VariantResponseDto createVariant(VariantCreateRequestDto request) {

        variantValidate.validateRequest(request);

        var entity = mapper.toEntity(request);
        var product = productRepository.findById(request.productId())
                .orElseThrow(() -> new EntityNotFoundException("Not found product with id=" + request.productId()));

        entity.setProduct(product);
        var created = repository.save(entity);

        return mapper.toResponseDto(created);
    }

    @Override
    @Transactional
    public void deleteVariantById(Long id) {
        var found = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found variant with id=" + id));

        repository.delete(found);
    }
}
