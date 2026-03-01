package com.whitechoke.productservice.api;

import com.whitechoke.productservice.domain.VariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class VariantController {

    private final VariantService service;
}
