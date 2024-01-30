package com.between.test.application.ports.output;

import com.between.test.domain.dto.PricesDto;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface PricesPort {
    Flux<PricesDto> getPricesByBrandAndProductAndDateRange(int brandId, long productId, LocalDateTime date);
}
