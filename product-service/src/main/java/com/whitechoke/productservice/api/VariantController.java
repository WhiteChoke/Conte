package com.whitechoke.productservice.api;

import com.whitechoke.productservice.api.dto.variantDto.VariantCreateRequestDto;
import com.whitechoke.productservice.api.dto.variantDto.VariantFilterDto;
import com.whitechoke.productservice.api.dto.variantDto.VariantFilterResponseDto;
import com.whitechoke.api.http.variant.VariantResponseDto;
import com.whitechoke.productservice.domain.VariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/variants")
public class VariantController {

    private final VariantService service;

    @GetMapping
    public ResponseEntity<VariantFilterResponseDto> getProductByFilter(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "productId", required = false) Long productId,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber
    ) {
        var filter = VariantFilterDto.builder()
                .id(id)
                .size(size)
                .productId(productId)
                .pageSize(pageSize)
                .pageNumber(pageNumber)
                .build();
        var found = service.getVariantByFilter(filter);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(found);
    }

    @PostMapping
    public ResponseEntity<VariantResponseDto> createProduct(
            @RequestBody VariantCreateRequestDto request
    ) {
        var created = service.createVariant(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){

        service.deleteVariantById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("ids")
    public ResponseEntity<List<VariantResponseDto>> getProductsByIds(
            @RequestBody List<Long> ids
    ) {
        var found = service.getVariantsByIds(ids);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(found);
    }
}
