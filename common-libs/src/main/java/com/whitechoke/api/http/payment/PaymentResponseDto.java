package com.whitechoke.api.http.payment;

import java.math.BigDecimal;
import java.time.Instant;

public record PaymentResponseDto(
        Long id,
        Long orderId,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        PaymentStatus paymentStatus,
        Long transactionalId,
        Instant createdAt
) {
}
