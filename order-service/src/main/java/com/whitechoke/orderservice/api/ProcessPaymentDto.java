package com.whitechoke.orderservice.api;

import com.whitechoke.api.http.payment.PaymentMethod;

public record ProcessPaymentDto(
        Long orderId,
        PaymentMethod paymentMethod
) {
}
