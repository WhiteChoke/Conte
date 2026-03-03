package com.whitechoke.orderservice.api.dto;

import com.whitechoke.orderservice.domain.db.order.DeliveryType;
import com.whitechoke.orderservice.domain.db.order.OrderStatus;
import java.math.BigDecimal;
import java.time.Instant;

public record OrderResponseDto(
        Long id,
        String customerPhone,
        String customerName,
        DeliveryType deliveryType,
        OrderStatus status,
        BigDecimal totalPrice,
        Instant createdAt
) {

}
