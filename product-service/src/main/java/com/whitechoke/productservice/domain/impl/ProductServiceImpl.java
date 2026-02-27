package com.whitechoke.productservice.domain.impl;

import com.whitechoke.productservice.api.dto.ProductFilterDto;
import com.whitechoke.productservice.api.dto.ProductFilterResponseDto;
import com.whitechoke.productservice.api.dto.ProductRequestDto;
import com.whitechoke.productservice.api.dto.ProductResponseDto;
import com.whitechoke.productservice.domain.ProductService;
import com.whitechoke.productservice.domain.db.ProductMapper;
import com.whitechoke.productservice.domain.db.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductFilterResponseDto getProductByFilter(ProductFilterDto filter) {

        var pageNumber = filter.pageNumber() != null
                ? filter.pageNumber()
                : 0;

        var pageSize = filter.pageSize() != null
                ? filter.pageSize()
                : 5;

        var pageable = Pageable.ofSize(pageSize).withPage(pageNumber);

        var found = repository.getProductByFilter(
                filter.id(),
                filter.productType(),
                filter.isAvailable(),
                pageable
        );

        var productDtoList = found.stream().map(mapper::toResponseDto).toList();

        return ProductFilterResponseDto.builder()
                .totalPages(found.getTotalPages())
                .totalElements(found.getTotalElements())
                .productResponse(productDtoList)
                .build();

    }

    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto request) {

        var entity = mapper.toProductEntity(request);
        var created = repository.save(entity);

        return mapper.toResponseDto(created);
    }
}
