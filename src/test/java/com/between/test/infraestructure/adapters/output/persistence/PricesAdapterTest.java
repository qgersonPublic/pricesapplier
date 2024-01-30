package com.between.test.infraestructure.adapters.output.persistence;

import com.between.test.domain.dto.PricesDto;
import com.between.test.domain.model.Prices;
import com.between.test.infraestructure.adapters.output.mapper.PriceMapper;
import com.between.test.infraestructure.adapters.output.repository.PricesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * @author : <a href="mailto:qgerson@gmail.com"> Gerson Quintero</a>
 */

@ExtendWith(MockitoExtension.class)
class PricesAdapterTest {

    @Mock
    private PricesRepository pricesRepository;
    @Mock
    private PriceMapper mapper;
    @InjectMocks
    private PricesAdapter pricesAdapter;

    @Test
    void testShouldFecthAndMapPrices(){
       var prices = Prices.builder()
                .id(1L)
                .brandId(1)
                .startDate(LocalDateTime.of(2020,6,14,0,0,0))
                .endDate(LocalDateTime.of(2020,12,31,23,59,59))
                .priceList(0)
                .productId(35455L)
                .priority(0)
                .price(new BigDecimal("35.50"))
                .currency("EUR")
                .build();

        var pricesDto = PricesDto.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020,6,14,0,0,0))
                .endDate(LocalDateTime.of(2020,12,31,23,59,59))
                .priceList(0)
                .productId(35455L)
                .priority(0)
                .price(new BigDecimal("35.50"))
                .currency("EUR")
                .build();


        given( pricesRepository.findByBrandAndProductAndDateRange(1,35455L, LocalDateTime.of(2020,6,14,10,0,0)))
                .willReturn(Flux.just(prices));

        given(mapper.mapTo(prices)).willReturn(pricesDto);

        Flux<PricesDto> pricesPublisher = pricesAdapter.getPricesByBrandAndProductAndDateRange(1,35455L, LocalDateTime.of(2020,6,14,10,0,0));

        StepVerifier.create(pricesPublisher)
                .assertNext( dto -> {
                    assertThat(dto.price()).isEqualTo(new BigDecimal("35.50"));
                    assertThat(dto.priceList()).isZero();
                    assertThat(dto.brandId()).isEqualTo(1);
                    assertThat(dto.startDate()).isEqualTo(LocalDateTime.of(2020,6,14,0,0,0));
                    assertThat(dto.endDate()).isEqualTo(LocalDateTime.of(2020,12,31,23,59,59));
                })
                .verifyComplete();
    }

}
