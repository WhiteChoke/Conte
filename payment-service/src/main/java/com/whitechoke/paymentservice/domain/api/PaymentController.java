package com.whitechoke.paymentservice.domain.api;

import com.whitechoke.api.http.payment.PaymentCreateRequestDto;
import com.whitechoke.api.http.payment.PaymentResponseDto;
import com.whitechoke.paymentservice.domain.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/payments")
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity<PaymentResponseDto> createPayment(
            @RequestBody PaymentCreateRequestDto request
    ) {
        var created = service.createPayment(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(created);
    }

    @GetMapping("{id}")
    public ResponseEntity<PaymentResponseDto> getPaymentById(
            @PathVariable Long id
    ){
        var found = service.getPaymentById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(found);
    }
}
