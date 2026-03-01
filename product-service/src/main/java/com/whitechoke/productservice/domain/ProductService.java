package com.whitechoke.productservice.domain;


import com.whitechoke.productservice.api.dto.productDto.ProductFilterDto;
import com.whitechoke.productservice.api.dto.productDto.ProductFilterResponseDto;
import com.whitechoke.productservice.api.dto.productDto.ProductCreateRequestDto;
import com.whitechoke.productservice.api.dto.productDto.ProductResponseDto;
import com.whitechoke.productservice.api.dto.productDto.ProductUpdateRequestDto;

public interface ProductService {
    ProductFilterResponseDto getProductByFilter(ProductFilterDto filter);
    ProductResponseDto createProduct(ProductCreateRequestDto request);
    void deleteProductById(Long id);
    ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto request);
}
