package com.between.test;

import com.between.test.domain.dto.PriceResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationIntegrationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    WebTestClient webTestClient;

    @BeforeEach
    void setUp(){
        webTestClient = WebTestClient.bindToApplicationContext(applicationContext)
                .configureClient().responseTimeout(Duration.ofSeconds(60)).build();
    }

    @Test
    @DisplayName("Test 1 at 10:00 14th day and product 35455 brand 1")
    void shouldTestScenario_Test1() {
        webTestClient.get()
                .uri(uri -> uri.path("/api/v1/get-price/")
                        .queryParam("brand","1")
                        .queryParam("product","35455")
                        .queryParam("date","2020-06-14T10:00:00")
                        .build()
                )
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON_VALUE)
                .expectBody(PriceResponseDto.class)
                .consumeWith( response -> {
                         var dto = response.getResponseBody();
                        assertNotNull(dto);
                        assertEquals(35455L,dto.productId(),"product identifier");
                        assertEquals(1,dto.brandId(),"brand identifier");
                        assertEquals(1,dto.priceList(),"Rate to be applied");
                        assertEquals(LocalDateTime.of(2020,6,14,0,0,0),
                                dto.startDate(),"starting date");
                        assertEquals(LocalDateTime.of(2020,12,31,23,59,59),
                                dto.endDate(),"ending date");
                        assertEquals(new BigDecimal("35.50"), dto.price(),"price to be applied");

                });
    }

    @Test
    @DisplayName("Test 2 at 16:00 14th day and product 35455 brand 1")
    void shouldSuccessScenario_Test2() {
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
                .consumeWith( response -> {
                    var dto = response.getResponseBody();
                    assertNotNull(dto);
                    assertEquals(35455L,dto.productId(),"product identifier");
                    assertEquals(1,dto.brandId(),"brand identifier");
                    assertEquals(2,dto.priceList(),"Rate to be applied");
                    assertEquals(LocalDateTime.of(2020,6,14,15,0,0),
                            dto.startDate(),"starting date");
                    assertEquals(LocalDateTime.of(2020,6,14,18,30,0),
                            dto.endDate(),"ending date");
                    assertEquals(new BigDecimal("25.45"), dto.price(),"price to be applied");

                });
    }

    @Test
    @DisplayName("Test 3 at 21:00 14th day and product 35455 brand 1")
    void shouldSuccessScenario_Test3() {
        webTestClient.get()
                .uri(uri -> uri.path("/api/v1/get-price/")
                        .queryParam("brand","1")
                        .queryParam("product","35455")
                        .queryParam("date","2020-06-14T21:00:00")
                        .build()
                )
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON_VALUE)
                .expectBody(PriceResponseDto.class)
                .consumeWith( response -> {
                    var dto = response.getResponseBody();
                    assertNotNull(dto);
                    assertEquals(35455L,dto.productId(),"product identifier");
                    assertEquals(1,dto.brandId(),"brand identifier");
                    assertEquals(1,dto.priceList(),"Rate to be applied");
                    assertEquals(LocalDateTime.of(2020,6,14,0,0,0),
                            dto.startDate(),"starting date");
                    assertEquals(LocalDateTime.of(2020,12,31,23,59,59),
                            dto.endDate(),"ending date");
                    assertEquals(new BigDecimal("35.50"), dto.price(),"price to be applied");
                });
    }

    @Test
    @DisplayName("Test 4 at 10:00 15th day and product 35455 brand 1")
    void shouldSuccessScenario_Test4() {
        webTestClient.get()
                .uri(uri -> uri.path("/api/v1/get-price/")
                        .queryParam("brand","1")
                        .queryParam("product","35455")
                        .queryParam("date","2020-06-15T10:00:00")
                        .build()
                )
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON_VALUE)
                .expectBody(PriceResponseDto.class)
                .consumeWith( response -> {
                    var dto = response.getResponseBody();
                    assertNotNull(dto);
                    assertEquals(35455L,dto.productId(),"product identifier");
                    assertEquals(1,dto.brandId(),"brand identifier");
                    assertEquals(3,dto.priceList(),"Rate to be applied");
                    assertEquals(LocalDateTime.of(2020,6,15,0,0,0),
                            dto.startDate(),"starting date");
                    assertEquals(LocalDateTime.of(2020,6,15,11,0,0),
                            dto.endDate(),"ending date");
                    assertEquals(new BigDecimal("30.50"), dto.price(),"price to be applied");

                });
    }

    @Test
    @DisplayName("Test 5 at 21:00 16th day and product 35455 brand 1")
    void shouldSuccessScenario_Test5() {
        webTestClient.get()
                .uri(uri -> uri.path("/api/v1/get-price/")
                        .queryParam("brand","1")
                        .queryParam("product","35455")
                        .queryParam("date","2020-06-16T21:00:00")
                        .build()
                )
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON_VALUE)
                .expectBody(PriceResponseDto.class)
                .consumeWith( response -> {
                    var dto = response.getResponseBody();
                    assertNotNull(dto);
                    assertEquals(35455L,dto.productId(),"product identifier");
                    assertEquals(1,dto.brandId(),"brand identifier");
                    assertEquals(4,dto.priceList(),"Rate to be applied");
                    assertEquals(LocalDateTime.of(2020,6,15,16,0,0),
                            dto.startDate(),"starting date");
                    assertEquals(LocalDateTime.of(2020,12,31,23,59,59),
                            dto.endDate(),"ending date");
                    assertEquals(new BigDecimal("38.95"), dto.price(),"price to be applied");

                });
    }

    @Test
    void testPricesNotFound() {
        webTestClient.get()
                .uri(
                        uri -> uri.path("/api/v1/get-price/")
                                .queryParam("brand","1")
                                .queryParam("product","354559")
                                .queryParam("date","2020-06-14T16:00:00")
                                .build()
                )
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.code").isEqualTo(404)
                .jsonPath("$.message").isEqualTo("Prices application not Found");

    }

}
