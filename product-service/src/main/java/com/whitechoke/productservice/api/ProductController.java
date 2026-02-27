package com.whitechoke.productservice.api;

import com.whitechoke.productservice.api.dto.ProductFilterResponseDto;
import com.whitechoke.productservice.api.dto.ProductRequestDto;
import com.whitechoke.productservice.api.dto.ProductFilterDto;
import com.whitechoke.productservice.api.dto.ProductResponseDto;
import com.whitechoke.productservice.domain.ProductService;
import com.whitechoke.productservice.domain.db.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<ProductFilterResponseDto> getProductByFilter(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "productType", required = false) ProductType productType,
            @RequestParam(name = "isAvailable", required = false) Boolean isAvailable,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber
    ) {
        var filter = ProductFilterDto.builder()
                .id(id)
                .productType(productType)
                .isAvailable(isAvailable)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
        var found = service.getProductByFilter(filter);

        return ResponseEntity.status(HttpStatus.OK).body(found);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestBody ProductRequestDto request
    ) {
        var created = service.createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
