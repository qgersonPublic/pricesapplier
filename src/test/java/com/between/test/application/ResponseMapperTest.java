package com.between.test.application;

import com.between.test.application.mappers.ResponseMapper;
import com.between.test.domain.dto.PricesDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ResponseMapperTest {

    @Test
    void shouldMapPriceDtoToResponse(){
        var mapper = Mappers.getMapper(ResponseMapper.class);

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

        var responseDto = mapper.mapTo(pricesDto);

        assertAll( () -> {
            assertNotNull(responseDto);
            assertEquals(pricesDto.productId(),responseDto.productId());
            assertEquals(pricesDto.brandId(),responseDto.brandId());
            assertEquals(pricesDto.priceList(),responseDto.priceList());
            assertEquals(pricesDto.startDate(),responseDto.startDate());
            assertEquals(pricesDto.endDate(),responseDto.endDate());
            assertEquals(pricesDto.price(),responseDto.price());
        });

    }
}
