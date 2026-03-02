package com.whitechoke.orderservice.domain.db;

public enum OrderStatus {
    PENDING_PAYMENT,
    PAID,
    PAYMENT_FAILED,
    DELIVERY_ASSIGNED,
    DELIVERED
}
