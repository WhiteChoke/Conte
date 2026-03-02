package com.whitechoke.orderservice.api.dto;

import com.whitechoke.orderservice.domain.db.order.DeliveryType;

public record OrderCreateRequestDto(
        String customerPhone,
        String customerName,
        DeliveryType deliveryType
) {
}
