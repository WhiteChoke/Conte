package com.whitechoke.orderservice.domain.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ProductHttpClientConfig {

    @Value("${http-url.product-service}")
    private String productServiceUrl;

    @Bean
    RestClient productRestClient() {
        return RestClient.builder()
                .baseUrl(productServiceUrl)
                .build();
    }

    @Bean
    ProductHttpClient productHttpClient() {
        return HttpServiceProxyFactory.builder()
                .exchangeAdapter(RestClientAdapter.create(productRestClient()))
                .build()
                .createClient(ProductHttpClient.class);
    }
}
