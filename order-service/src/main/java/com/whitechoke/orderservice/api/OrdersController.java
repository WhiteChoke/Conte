package com.whitechoke.orderservice.api;

import com.whitechoke.orderservice.api.dto.OrderCreateRequestDto;
import com.whitechoke.orderservice.api.dto.OrderResponseDto;
import com.whitechoke.orderservice.domain.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/orders")
public class OrdersController {

    OrderService service;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @RequestBody OrderCreateRequestDto request
    ) {
        var created = service.createOrder(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(created);
    }
}
