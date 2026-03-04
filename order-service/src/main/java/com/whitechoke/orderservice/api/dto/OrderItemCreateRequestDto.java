package com.whitechoke.orderservice.api.dto;

public record OrderItemCreateRequestDto(
        Long variantId,
        Integer quantity
) {
}
