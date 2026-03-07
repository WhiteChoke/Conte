package com.whitechoke.api.http.payment;

import java.time.Instant;

public record PaymentResponseDto(
        Long id,
        Long orderId,
        PaymentMethod paymentMethod,
        PaymentStatus paymentStatus,
        Long transactionalId,
        Instant createdAt
) {
}
