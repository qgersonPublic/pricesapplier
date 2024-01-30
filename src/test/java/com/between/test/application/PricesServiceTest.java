package com.between.test.application;

import com.between.test.application.mappers.ResponseMapper;
import com.between.test.application.ports.output.PricesPort;
import com.between.test.application.service.PricesService;
import com.between.test.domain.dto.PriceResponseDto;
import com.between.test.domain.dto.PricesDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PricesServiceTest {
    @Mock
    private PricesPort pricesPort;

    @Mock
    private ResponseMapper responseMapper;

    @InjectMocks
    private PricesService pricesService;

    @Test
    void shouldRetrieveProperPrice(){
        var dto1 = PricesDto.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020,6,14,0,0,0))
                .endDate(LocalDateTime.of(2020,12,31,23,59,59))
                .priceList(0)
                .productId(35455L)
                .priority(0)
                .price(new BigDecimal("35.50"))
                .currency("EUR")
                .build();

        var dto2 = PricesDto.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020,6,14,15,0,0))
                .endDate(LocalDateTime.of(2020,6,14,18,30,0))
                .priceList(2)
                .productId(35455L)
                .priority(1)
                .price(new BigDecimal("25.45"))
                .currency("EUR")
                .build();

        var response = PriceResponseDto.builder()
                .productId(35455L)
                .brandId(1)
                .priceList(2)
                .startDate(LocalDateTime.of(2020,6,14,15,0,0))
                .endDate(LocalDateTime.of(2020,6,14,18,30,0))
                .price(new BigDecimal("25.45"))
                .build();

        given(pricesPort.getPricesByBrandAndProductAndDateRange(1,35455L, LocalDateTime.of(2020,6,14,16,0,0)))
                .willReturn(Flux.just(dto2,dto1));

        given(responseMapper.mapTo(dto2)).willReturn(response);

        Mono<PriceResponseDto> publisher =  this.pricesService.fetchPriceToApply(1,35455L, LocalDateTime.of(2020,6,14,16,0,0));

        StepVerifier.create(publisher)
                .assertNext( price -> {
                    assertThat(price.productId()).isEqualTo(35455L);
                    assertThat(price.brandId()).isEqualTo(1);
                    assertThat(price.price()).isEqualTo(new BigDecimal("25.45"));
                    assertThat(price.priceList()).isEqualTo(2);
                })
                .verifyComplete();

    }
}
