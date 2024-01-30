package com.between.test.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p> Performs unit test over the {@link Prices} POJO and assert its functionality</p>
 * @author : <a href="mailto:qgerson@gmail.com"> Gerson Quintero</a>
 */

class PricesPOJOTest {
    private Prices price;

    @BeforeEach
    void init(){
        price = Prices.builder()
            .id(1L)
            .brandId(1)
            .startDate(LocalDateTime.of(2020,6,14,0,0,0))
            .endDate(LocalDateTime.of(2020,12,31,23,59,59))
            .priceList(1)
            .productId(35455L)
            .priority(0)
            .price(new BigDecimal("35.50"))
            .currency("EUR")
            .build();
    }

    @Test
    @DisplayName("Creation of prices Instance")
    void verifyPricesInstance_isOk(){
        assertAll(() -> {
            assertNotNull(price);
            assertEquals(1, price.getId());
            assertEquals(1, price.getBrandId());
            assertEquals("2020-06-14T00:00", price.getStartDate().toString());
            assertEquals("2020-12-31T23:59:59", price.getEndDate().toString());
            assertEquals(1, price.getPriceList());
            assertEquals(35455L, price.getProductId());
            assertEquals(0, price.getPriority());
            assertEquals(new BigDecimal("35.50"),price.getPrice());
            assertEquals("EUR", price.getCurrency());
        });

    }
}
