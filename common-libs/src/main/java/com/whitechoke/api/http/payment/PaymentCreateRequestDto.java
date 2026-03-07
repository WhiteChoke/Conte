package com.whitechoke.api.http.payment;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentCreateRequestDto(
        Long orderId,
        PaymentMethod paymentMethod,
        BigDecimal amount
) {
}
