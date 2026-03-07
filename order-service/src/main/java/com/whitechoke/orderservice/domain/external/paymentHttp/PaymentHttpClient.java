package com.whitechoke.orderservice.domain.external.paymentHttp;

import com.whitechoke.api.http.payment.PaymentCreateRequestDto;
import com.whitechoke.api.http.payment.PaymentResponseDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(
        accept = "application/json",
        contentType = "application/json",
        url = "/api/v1/payments"
)
public interface PaymentHttpClient {

    @PostExchange
    PaymentResponseDto createPayment(@RequestBody PaymentCreateRequestDto request);
}
