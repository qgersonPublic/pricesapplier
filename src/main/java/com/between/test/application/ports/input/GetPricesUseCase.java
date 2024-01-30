package com.between.test.application.ports.input;

import com.between.test.domain.dto.PriceResponseDto;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface GetPricesUseCase {

    Mono<PriceResponseDto> fetchPriceToApply(int brandId, long productId, LocalDateTime date);
}
