package com.whitechoke.orderservice.domain;

import com.whitechoke.orderservice.api.dto.OrderCreateRequestDto;
import org.mapstruct.ap.shaded.freemarker.template.utility.NullArgumentException;
import org.springframework.stereotype.Component;

@Component
public class ValidateOrder {

    public void validateRequest(OrderCreateRequestDto request) {
        if (request.customerName() == null) {
            throw new NullArgumentException("Customer name cannot be null");
        }
        if (request.customerPhone() == null) {
            throw new NullArgumentException("Customer phone cannot be null");
        }
        if (request.customerName().isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        if (request.customerPhone().isBlank()) {
            throw new IllegalArgumentException("Customer phone cannot be empty");
        }
        if (request.deliveryType() == null){
            throw new NullArgumentException("Delivery type cannot be null");
        }
        if (request.orderItemCreateRequestDtoList() == null) {
            throw new IllegalArgumentException("Picked items cannot be empty");
        }
    }
}
