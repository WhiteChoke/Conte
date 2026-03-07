package com.whitechoke.orderservice.domain.external.paymentHttp;

import com.whitechoke.orderservice.domain.external.productHttp.ProductHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class PaymentHttpClientConfig {

    @Value("${http-url.payment-service}")
    private String paymentServiceUrl;

    @Bean
    RestClient paymentRestClient() {
        return RestClient.builder()
                .baseUrl(paymentServiceUrl)
                .build();
    }

    @Bean
    PaymentHttpClient paymentHttpClient() {
        return HttpServiceProxyFactory.builder()
                .exchangeAdapter(RestClientAdapter.create(paymentRestClient()))
                .build()
                .createClient(PaymentHttpClient.class);
    }
}
