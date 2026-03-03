package com.whitechoke.productservice.domain;


import com.whitechoke.api.http.product.ProductResponseDto;
import com.whitechoke.productservice.api.dto.productDto.ProductFilterDto;
import com.whitechoke.productservice.api.dto.productDto.ProductFilterResponseDto;
import com.whitechoke.productservice.api.dto.productDto.ProductCreateRequestDto;

import com.whitechoke.productservice.api.dto.productDto.ProductUpdateRequestDto;

import java.util.List;

public interface ProductService {
    ProductFilterResponseDto getProductByFilter(ProductFilterDto filter);
    ProductResponseDto createProduct(ProductCreateRequestDto request);
    void deleteProductById(Long id);
    ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto request);
    List<ProductResponseDto> getProductsByIds(List<Long> ids);
}
