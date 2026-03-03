package com.whitechoke.productservice.domain.impl;

import com.whitechoke.productservice.api.dto.productDto.ProductCreateRequestDto;
import com.whitechoke.productservice.api.dto.productDto.ProductFilterDto;
import com.whitechoke.productservice.api.dto.productDto.ProductFilterResponseDto;
import com.whitechoke.productservice.api.dto.productDto.ProductResponseDto;
import com.whitechoke.productservice.api.dto.productDto.ProductUpdateRequestDto;
import com.whitechoke.productservice.domain.ProductService;
import com.whitechoke.productservice.domain.validation.ProductValidate;
import com.whitechoke.productservice.domain.db.product.ProductMapper;
import com.whitechoke.productservice.domain.db.product.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final ProductValidate productValidate;

    @Override
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

    @Override
    @Transactional
    public ProductResponseDto createProduct(ProductCreateRequestDto request) {

        productValidate.validateRequest(request);

        var entity = mapper.toProductEntity(request);
        entity.setIsAvailable(true);

        var created = repository.save(entity);

        return mapper.toResponseDto(created);
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        var found = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found product with id=" + id));
        repository.delete(found);
    }

    @Override
    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto request) {

        productValidate.validateRequest(request);

        var found = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found product with id=" + id));

        found.setName(request.name());
        found.setDescription(request.description());
        found.setBasePrice(request.basePrice());

        return mapper.toResponseDto(found);
    }

    @Override
    public List<ProductResponseDto> getProductsByIds(List<Long> ids) {
        var found = repository.findAllById(ids);
        return found
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }
}
