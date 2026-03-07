package com.whitechoke.orderservice.domain;


import com.whitechoke.orderservice.api.ProcessPaymentDto;
import com.whitechoke.orderservice.api.dto.OrderCreateRequestDto;
import com.whitechoke.orderservice.api.dto.OrderResponseDto;

public interface OrderService  {
    OrderResponseDto createOrder(OrderCreateRequestDto request);
    OrderResponseDto getOrderById(Long id);
    OrderResponseDto processPayment(ProcessPaymentDto request);
}
