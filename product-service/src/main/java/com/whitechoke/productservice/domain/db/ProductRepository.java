package com.whitechoke.productservice.domain.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("""
        SELECT p FROM ProductEntity p
                WHERE (:id IS NULL OR p.id = :id)
                AND (:productType IS NULL OR p.productType = :productType)
                AND (:isAvailable IS NULL OR p.isAvailable = :isAvailable)
        """)
    Page<ProductEntity> getProductByFilter(@Param("id") Long id,
                                           @Param("productType") ProductType productType,
                                           @Param("isAvailable") Boolean isAvailable,
                                           Pageable pageable);
}
