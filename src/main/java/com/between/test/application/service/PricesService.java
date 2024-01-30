package com.between.test.application.service;

import com.between.test.application.mappers.ResponseMapper;
import com.between.test.application.ports.input.GetPricesUseCase;
import com.between.test.application.ports.output.PricesPort;
import com.between.test.domain.dto.PriceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PricesService implements GetPricesUseCase {

    private final PricesPort pricesPort;
    private final ResponseMapper mapper;

    @Override
    public Mono<PriceResponseDto> fetchPriceToApply(int brandId, long productId, LocalDateTime date) {
        return pricesPort.getPricesByBrandAndProductAndDateRange(brandId, productId, date)
                .next()
                .map(mapper::mapTo);
    }
}
