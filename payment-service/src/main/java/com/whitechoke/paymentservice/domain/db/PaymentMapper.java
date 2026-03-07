package com.whitechoke.paymentservice.domain.db;

import com.whitechoke.api.http.payment.PaymentCreateRequestDto;
import com.whitechoke.api.http.payment.PaymentResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface PaymentMapper {
    PaymentResponseDto toRequest(PaymentEntity entity);
    PaymentEntity toEntity(PaymentCreateRequestDto request);
}
