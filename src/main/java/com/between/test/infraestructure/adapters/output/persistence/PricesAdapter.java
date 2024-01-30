package com.between.test.infraestructure.adapters.output.persistence;

import com.between.test.application.exception.DataBaseOperationException;
import com.between.test.application.exception.ResourceNotFound;
import com.between.test.application.ports.output.PricesPort;
import com.between.test.domain.dto.PricesDto;
import com.between.test.infraestructure.adapters.output.mapper.PriceMapper;
import com.between.test.infraestructure.adapters.output.repository.PricesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PricesAdapter implements PricesPort {

    private final PricesRepository repository;
    private final PriceMapper mapper;
    @Override
    public Flux<PricesDto> getPricesByBrandAndProductAndDateRange(int brandId, long productId, LocalDateTime date) {
        return repository.findByBrandAndProductAndDateRange(brandId,productId,date)
                .onErrorResume(error -> Mono.error(new DataBaseOperationException(error.getMessage())))
                .switchIfEmpty(Mono.error(new ResourceNotFound("Prices application not Found")))
                .map(mapper::mapTo);
    }
}
