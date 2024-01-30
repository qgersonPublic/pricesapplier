package com.between.test.infraestructure.adapters.output.repository;

import com.between.test.domain.model.Prices;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface PricesRepository extends ReactiveCrudRepository<Prices,Long> {
    @Query("SELECT * FROM PRICES WHERE BRAND_ID = $1 AND PRODUCT_ID = $2 AND START_DATE <= $3 AND END_DATE >= $3" +
            " ORDER BY PRIORITY DESC, PRICE ")
    Flux<Prices> findByBrandAndProductAndDateRange(Integer brandId, Long productId,LocalDateTime date);
}
