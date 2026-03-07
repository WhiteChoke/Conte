package com.whitechoke.api.http.payment;

public record PaymentCreateRequestDto(
        Long orderId,
        PaymentMethod paymentMethod
) {
}
