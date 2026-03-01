package com.whitechoke.productservice.domain;


import com.whitechoke.productservice.api.dto.ProductFilterDto;
import com.whitechoke.productservice.api.dto.ProductFilterResponseDto;
import com.whitechoke.productservice.api.dto.ProductRequestDto;
import com.whitechoke.productservice.api.dto.ProductResponseDto;
import com.whitechoke.productservice.api.dto.ProductUpdateRequestDto;

public interface ProductService {
    ProductFilterResponseDto getProductByFilter(ProductFilterDto filter);
    ProductResponseDto createProduct(ProductRequestDto request);
    void deleteProductById(Long id);
    ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto request);
}
