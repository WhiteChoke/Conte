package com.whitechoke.productservice.api.dto.productDto;

import java.math.BigDecimal;

public interface ProductDto {
    String name();
    BigDecimal basePrice();
    String description();
}
