package com.whitechoke.paymentservice.domain;

import com.whitechoke.api.http.payment.PaymentCreateRequestDto;
import com.whitechoke.api.http.payment.PaymentResponseDto;

public interface PaymentService {
    PaymentResponseDto createPayment(PaymentCreateRequestDto request);
    PaymentResponseDto getPaymentById(Long id);
}
