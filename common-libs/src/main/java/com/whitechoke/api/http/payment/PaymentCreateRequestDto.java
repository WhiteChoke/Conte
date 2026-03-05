package com.whitechoke.api.http.payment;

import java.time.Instant;

public record PaymentCreateRequestDto(
        Long orderId,
        PaymentMethod paymentMethod,
        Instant paidAt
) {
}
