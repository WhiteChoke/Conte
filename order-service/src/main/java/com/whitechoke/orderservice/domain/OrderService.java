package com.whitechoke.orderservice.domain;

import com.whitechoke.orderservice.api.dto.OrderCreateRequestDto;
import com.whitechoke.orderservice.api.dto.OrderResponsetDto;

public interface OrderService  {
    OrderResponsetDto createOrder(OrderCreateRequestDto request);
}
