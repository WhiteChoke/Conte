package com.whitechoke.productservice.domain.db.variant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VariantsRepository extends JpaRepository<VariantEntity, Long> {
    @Query("""
        SELECT v FROM VariantEntity v
                WHERE (:id IS NULL OR v.id = :id)
                AND (:size IS NULL OR v.size = :size)
                AND (:productId IS NULL OR v.product.id = :productId)
        """)
    Page<VariantEntity> getVariantByFilter(@Param("id") Long id,
                                            @Param("size") Integer size,
                                            @Param("productId") Long productId,
                                            Pageable pageable);
}
