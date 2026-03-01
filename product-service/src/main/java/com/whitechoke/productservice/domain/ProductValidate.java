package com.whitechoke.productservice.domain;

import com.whitechoke.productservice.api.dto.ProductRequestDto;
import org.mapstruct.ap.shaded.freemarker.template.utility.NullArgumentException;
import org.springframework.stereotype.Component;

@Component
public class ProductValidate {

    public void validateRequest(ProductRequestDto request) {
        if (request.productType() == null) {
            throw new NullArgumentException("Product type cant be null");
        }
        if (request.basePrice() == null) {
            throw new NullArgumentException("Product base price cant be null");
        }
        if (request.name() == null) {
            throw new NullArgumentException("Product name cant be null");
        }
        if (request.name().isBlank()) {
            throw new IllegalArgumentException("Product name cant be empty");
        }
        if (request.description() == null) {
            throw new NullArgumentException("Product description cant be null");
        }
        if (request.description().isBlank()) {
            throw new IllegalArgumentException("Product description cant be empty");
        }
    }
}
