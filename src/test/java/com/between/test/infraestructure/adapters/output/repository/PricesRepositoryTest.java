package com.between.test.infraestructure.adapters.output.repository;

import com.between.test.domain.model.Prices;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * <p> Performs unit test over the {@link PricesRepository} Bean verify its steps and correctness </p>
 * @author : <a href="mailto:qgerson@gmail.com"> Gerson Quintero</a>
 */

@DataR2dbcTest
class PricesRepositoryTest {

    @Autowired
    PricesRepository pricesRepository;

    @Test
    void testRepositoryBeanExists(){
        assertThat(pricesRepository).isNotNull();
    }

    @Test
    @DisplayName("Repository should fecth all data")
    void testShouldRetrieveAll(){
        StepVerifier.create(pricesRepository.findAll())
                .consumeNextWith(p1 -> assertThat(p1.getId()).isEqualTo(1L))
                .consumeNextWith(p2 -> assertThat(p2.getPrice()).isEqualTo(new BigDecimal("25.45")))
                .consumeNextWith(p3 -> assertThat(p3.getPriceList()).isEqualTo(3))
                .consumeNextWith(p4 -> assertThat(p4.getStartDate()).isEqualTo(LocalDateTime.of(2020,6,15,16,0,0)))
                .verifyComplete();
    }

    @Test
    @DisplayName("Repository should be retrieve in date range")
    void testShouldRetrieveBetweenDateAndProductAndBrand(){
        Flux<Prices> publisher = pricesRepository
                .findByBrandAndProductAndDateRange(1,35455L, LocalDateTime.of(2020,6,14,10,0,0));

        StepVerifier.create(publisher)
                .expectNextMatches(price -> price.getPrice().equals(new BigDecimal("35.50")))
                .verifyComplete();
    }
}
