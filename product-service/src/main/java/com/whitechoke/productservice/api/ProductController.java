package com.whitechoke.productservice.api;

import com.whitechoke.productservice.api.dto.productDto.ProductCreateRequestDto;
import com.whitechoke.productservice.api.dto.productDto.ProductFilterResponseDto;
import com.whitechoke.productservice.api.dto.productDto.ProductFilterDto;
import com.whitechoke.productservice.api.dto.productDto.ProductResponseDto;
import com.whitechoke.productservice.api.dto.productDto.ProductUpdateRequestDto;
import com.whitechoke.productservice.domain.ProductService;
import com.whitechoke.productservice.domain.db.product.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(found);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestBody ProductCreateRequestDto request
    ) {
        var created = service.createProduct(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){

        service.deleteProductById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductUpdateRequestDto request
    ) {
        var updated = service.updateProduct(id, request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updated);
    }

    @GetMapping("ids")
    public ResponseEntity<List<ProductResponseDto>> getProductsByIds(
            @RequestBody List<Long> ids
    ) {
        var found = service.getProductsByIds(ids);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(found);
    }
}
