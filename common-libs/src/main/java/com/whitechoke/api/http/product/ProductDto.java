package com.whitechoke.api.http.product;

import java.math.BigDecimal;

public interface ProductDto {
    String name();
    BigDecimal basePrice();
    String description();
}
