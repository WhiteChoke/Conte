package com.whitechoke.orderservice.domain.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class HttpClientConfig {

    @Value("${http-url.product-service}")
    private String productServiceUrl;

    @Bean
    RestClient 
}
