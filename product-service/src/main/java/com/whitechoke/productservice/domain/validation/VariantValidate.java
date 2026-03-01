package com.whitechoke.productservice.domain.validation;

import com.whitechoke.productservice.api.dto.variantDto.VariantCreateRequestDto;
import org.mapstruct.ap.shaded.freemarker.template.utility.NullArgumentException;
import org.springframework.stereotype.Component;

@Component
public class VariantValidate {

    public void validateRequest(VariantCreateRequestDto request) {
        if (request.priceModifier() == null) {
            throw new NullArgumentException("Variant price modifier cant be null");
        }
        if (request.size() == null) {
            throw new NullArgumentException("Variant size cant be null");
        }
        if (request.productId() == null) {
            throw new NullArgumentException("Product id cant be null");
        }
    }
}
