package com.whitechoke.orderservice.domain.impl;

import com.whitechoke.api.http.payment.PaymentCreateRequestDto;
import com.whitechoke.api.http.payment.PaymentResponseDto;
import com.whitechoke.api.http.payment.PaymentStatus;
import com.whitechoke.api.kafka.OrderPaidEvent;
import com.whitechoke.orderservice.api.ProcessPaymentDto;
import com.whitechoke.orderservice.api.dto.OrderCreateRequestDto;
import com.whitechoke.orderservice.api.dto.OrderItemCreateRequestDto;
import com.whitechoke.orderservice.api.dto.OrderResponseDto;
import com.whitechoke.orderservice.domain.OrderService;
import com.whitechoke.orderservice.domain.ValidateOrder;
import com.whitechoke.orderservice.domain.db.OrderMapper;
import com.whitechoke.orderservice.domain.db.order.DeliveryType;
import com.whitechoke.orderservice.domain.db.order.OrderEntity;
import com.whitechoke.orderservice.domain.db.order.OrderRepository;
import com.whitechoke.orderservice.domain.db.order.OrderStatus;
import com.whitechoke.orderservice.domain.db.orderItem.OrderItemEntity;
import com.whitechoke.orderservice.domain.db.orderItem.OrderItemRepository;
import com.whitechoke.orderservice.domain.external.paymentHttp.PaymentHttpClient;
import com.whitechoke.orderservice.domain.external.productHttp.ProductHttpClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final ValidateOrder validateOrder;
    private final ProductHttpClient productHttpClient;
    private final OrderMapper mapper;
    private final OrderItemRepository orderItemRepository;
    private final PaymentHttpClient paymentHttpClient;
    private final KafkaTemplate<Long, OrderPaidEvent> kafkaTemplate;

    @Value("${order-paid-topic}")
    private String orderPaidTopic;

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderCreateRequestDto request) {

        validateOrder.validateRequest(request);
        var entity = mapper.toEntity(request);

        var variantsId = request
                .orderItemCreateRequestDtoList()
                .stream()
                .map(OrderItemCreateRequestDto::variantId)
                .toList();

        var variants = productHttpClient.getVariantsByIds(variantsId);

        Map<Long, Integer> quantityByVariantId = request.orderItemCreateRequestDtoList()
                .stream()
                .collect(Collectors.toMap(
                        OrderItemCreateRequestDto::variantId,
                        OrderItemCreateRequestDto::quantity
                ));

        var orderItems = variants
                .stream()
                .map(variant -> new OrderItemEntity(
                        entity,
                        variant.id(),
                        variant.product().id(),
                        quantityByVariantId.get(variant.id()),
                        variant.product().basePrice().add(BigDecimal.valueOf(variant.priceModifier())))
                )
                .toList();

        var totalPrice = calculateTotalPrice(orderItems);

        entity.setCreatedAt(Instant.now());
        entity.setStatus(OrderStatus.PENDING_PAYMENT);
        entity.setTotalPrice(totalPrice);

        var created = repository.save(entity);
        orderItemRepository.saveAll(orderItems);

        return mapper.toResponseDto(created);
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        var found = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found order with id=" + id));
        return mapper.toResponseDto(found);
    }

    @Override
    @Transactional
    public OrderResponseDto processPayment(ProcessPaymentDto request) {

        var entity = repository.findById(request.orderId())
                .orElseThrow(() -> new EntityNotFoundException("Not found order with id=" + request.orderId()));

        if (!entity.getStatus().equals(OrderStatus.PENDING_PAYMENT)) {
            throw new IllegalStateException("Order status must be PENDING_PAYMENT");
        }

        var response = paymentHttpClient.createPayment(
                PaymentCreateRequestDto
                        .builder()
                        .paymentMethod(request.paymentMethod())
                        .orderId(request.orderId())
                        .amount(entity.getTotalPrice())
                        .build()
        );

        var status = response.paymentStatus().equals(PaymentStatus.SUCCESSFUL)
                ? OrderStatus.PAID
                : OrderStatus.PAYMENT_FAILED;

        entity.setStatus(status);
        if (status.equals(OrderStatus.PAID) && entity.getDeliveryType().equals(DeliveryType.COURIER)) {
            sendOrderPaidEvent(entity, response);
        }

        return mapper.toResponseDto(entity);
    }

    private void sendOrderPaidEvent(
            OrderEntity entity,
            PaymentResponseDto response
    ) {
        kafkaTemplate.send(
                orderPaidTopic,
                entity.getId(),
                OrderPaidEvent.builder()
                        .orderId(entity.getId())
                        .paymentMethod(response.paymentMethod())
                        .paymentId(response.id())
                        .amount(response.amount())
                        .build()
        );
    }

    private BigDecimal calculateTotalPrice(List<OrderItemEntity> items) {
        var totalPrice = BigDecimal.ZERO;
        for (OrderItemEntity entity : items) {
            totalPrice = entity.getUnitPrice()
                    .multiply(BigDecimal.valueOf(entity.getQuantity()))
                    .add(totalPrice);
        }
        return totalPrice;
    }

}
