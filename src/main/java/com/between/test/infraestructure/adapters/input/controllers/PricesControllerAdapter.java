package com.between.test.infraestructure.adapters.input.controllers;

import com.between.test.application.service.PricesService;
import com.between.test.domain.dto.PriceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 *
 */

@Tag(name = "Prices Applied Service", description = "This EP enables to retrieve the proper prices given the brand, product and date-time period")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class PricesControllerAdapter {

    private final PricesService pricesService;

    @Operation(
            summary = "EP Performs call for fetching the proper price to be applied",
            description = "GET Verb service who enables perform call upon retrieving price to be applied due to given brand, product and date-time"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = PriceResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "NOT FOUND",
                            content = @Content(schema = @Schema(implementation = RuntimeException.class)))
            }
    )
    @GetMapping(value = "/get-price/")
    public Mono<PriceResponseDto> callForProperPrice(@RequestParam("brand") final int brandId,
                                                     @RequestParam("product") final long productId,
                                                     @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                                        final LocalDateTime date){

        return pricesService.fetchPriceToApply(brandId,productId,date);

    }

}
