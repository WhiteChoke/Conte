package com.whitechoke.productservice.domain.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", nullable = false)
    private ProductType productType;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "base_price", nullable = false)
    private BigDecimal basePrice;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;
}
