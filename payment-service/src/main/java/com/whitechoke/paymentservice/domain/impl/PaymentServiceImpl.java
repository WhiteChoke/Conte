package com.whitechoke.paymentservice.domain.impl;

import com.whitechoke.api.http.payment.PaymentCreateRequestDto;
import com.whitechoke.api.http.payment.PaymentMethod;
import com.whitechoke.api.http.payment.PaymentResponseDto;
import com.whitechoke.api.http.payment.PaymentStatus;
import com.whitechoke.paymentservice.domain.PaymentService;
import com.whitechoke.paymentservice.domain.db.PaymentMapper;
import com.whitechoke.paymentservice.domain.db.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Random;
import java.util.random.RandomGenerator;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;

    @Override
    @Transactional
    public PaymentResponseDto createPayment(PaymentCreateRequestDto request) {

        var found = repository.findByOrderId(request.orderId());

        if (found.isPresent()) {
            log.info("Payment already exist for product with id={}", request.orderId());
            return mapper.toRequest(found.get());
        }

        var entity = mapper.toEntity(request);

        var status = request.paymentMethod().equals(PaymentMethod.CASH)
                ? PaymentStatus.DENIED
                : PaymentStatus.SUCCESSFUL;

        entity.setCreatedAt(Instant.now());
        entity.setTransactionalId(
                Random
                .from(RandomGenerator.getDefault())
                .nextLong()
        );
        entity.setPaymentStatus(status);
        entity.setAmount(request.amount());

        var created = repository.save(entity);

        return mapper.toRequest(created);
    }

    @Override
    public PaymentResponseDto getPaymentById(Long id) {
        var found = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found payment with id=" + id));

        return mapper.toRequest(found);
    }
}
