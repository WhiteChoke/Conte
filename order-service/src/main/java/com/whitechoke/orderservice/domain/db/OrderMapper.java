package com.whitechoke.orderservice.domain.db;

import com.whitechoke.orderservice.api.dto.OrderCreateRequestDto;
import com.whitechoke.orderservice.api.dto.OrderResponseDto;
import com.whitechoke.orderservice.domain.db.order.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface OrderMapper {
    OrderEntity toEntity(OrderCreateRequestDto request);
    OrderResponseDto toResponseDto(OrderEntity entity);
}
