package com.between.test.infraestructure.adapters.input;

import com.between.test.application.exception.GlobalErrorAttributes;
import com.between.test.application.service.PricesService;
import com.between.test.domain.dto.PriceResponseDto;
import com.between.test.infraestructure.adapters.input.controllers.PricesControllerAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebFluxTest({PricesControllerAdapter.class, GlobalErrorAttributes.class})
class PricesControllerAdapterTest {

    @MockBean
    private PricesService service;

    @Autowired
    WebTestClient webTestClient;

    @Test
    @DisplayName("Get Price controller Test")
    void shouldGetPriceAndResponse(){
        var response = PriceResponseDto.builder()
                .productId(35455L)
                .brandId(1)
                .priceList(2)
                .startDate(LocalDateTime.of(2020,6,14,15,0,0))
                .endDate(LocalDateTime.of(2020,6,14,18,30,0))
                .price(new BigDecimal("25.45"))
                .build();

        given(service.fetchPriceToApply(1,35455L, LocalDateTime.of(2020,6,14,16,0,0)))
                .willReturn(Mono.just(response));

        webTestClient.get()
                .uri(uri -> uri.path("/api/v1/get-price/")
                        .queryParam("brand","1")
                        .queryParam("product","35455")
                        .queryParam("date","2020-06-14T16:00:00")
                        .build()
                )
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON_VALUE)
                .expectBody(PriceResponseDto.class)
                .value(PriceResponseDto::price,equalTo(new BigDecimal("25.45")));
    }
}
