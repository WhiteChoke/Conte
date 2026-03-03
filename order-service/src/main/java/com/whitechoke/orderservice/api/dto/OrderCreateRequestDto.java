package com.whitechoke.orderservice.api.dto;

import com.whitechoke.orderservice.domain.db.order.DeliveryType;

import java.util.List;

public record OrderCreateRequestDto(
        String customerPhone,
        String customerName,
        DeliveryType deliveryType,
        List<Long> productIds
) {
}
