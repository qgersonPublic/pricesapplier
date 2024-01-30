package com.between.test.infraestructure.adapters.output.mappers;

import com.between.test.domain.model.Prices;
import com.between.test.infraestructure.adapters.output.mapper.PriceMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PriceMapperTest {

    @Test
    void shouldMapPricesToPricesDto(){
        var mapper = Mappers.getMapper(PriceMapper.class);
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

        var pricesDto = mapper.mapTo(prices);

        assertAll( () -> {
           assertNotNull(pricesDto);
           assertEquals(prices.getBrandId(),pricesDto.brandId());
            assertEquals(prices.getPrice(),pricesDto.price());
            assertEquals(prices.getStartDate(),pricesDto.startDate());
            assertEquals(prices.getEndDate(),pricesDto.endDate());
            assertEquals(prices.getCurrency(),pricesDto.currency());
            assertEquals(prices.getPriority(),pricesDto.priority());
            assertEquals(prices.getProductId(),pricesDto.productId());
        });
    }
}
