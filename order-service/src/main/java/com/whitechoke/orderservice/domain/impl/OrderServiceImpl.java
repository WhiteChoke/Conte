package com.whitechoke.orderservice.domain.impl;

import com.whitechoke.orderservice.api.dto.OrderCreateRequestDto;
import com.whitechoke.orderservice.api.dto.OrderItemCreateRequestDto;
import com.whitechoke.orderservice.api.dto.OrderResponseDto;
import com.whitechoke.orderservice.domain.OrderService;
import com.whitechoke.orderservice.domain.ValidateOrder;
import com.whitechoke.orderservice.domain.db.OrderMapper;
import com.whitechoke.orderservice.domain.db.order.OrderRepository;
import com.whitechoke.orderservice.domain.db.order.OrderStatus;
import com.whitechoke.orderservice.domain.db.orderItem.OrderItemEntity;
import com.whitechoke.orderservice.domain.db.orderItem.OrderItemRepository;
import com.whitechoke.orderservice.domain.external.ProductHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final ValidateOrder validateOrder;
    private final ProductHttpClient productHttpClient;
    private final OrderMapper mapper;
    private final OrderItemRepository orderItemRepository;


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
