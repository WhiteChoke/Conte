package com.whitechoke.orderservice.domain.external;

import com.whitechoke.api.http.variant.VariantResponseDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange(
        accept = "application/json",
        contentType = "application/json",
        url = "/api/v1/variants"
)
public interface ProductHttpClient {

    @GetExchange(url = "ids")
    List<VariantResponseDto> getVariantsByIds(@RequestBody List<Long> ids);
}
