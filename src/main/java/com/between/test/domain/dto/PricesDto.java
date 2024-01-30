package com.between.test.domain.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Record immutable type for transferring data porpuse only
 *
 * @author : <a href="mailto:qgerson@gmail.com"> Gerson Quintero</a>
 * @param brandId Brand identifier
 * @param startDate LocalDateTime type who represents the beginning period of the price to be applied
 * @param endDate LocalDateTime type who represents the ending period of the price to be applied
 * @param priceList Applicable pricing rate identifier
 * @param productId Product Identifier
 * @param priority Price application disambiguator. If two rates coincide in a range of dates, the one with the highest priority (highest numerical value) is applied.
 * @param price Price to be applied
 * @param currency Price's currency
 */
@Builder
public record PricesDto(
        Integer brandId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priceList,
        Long productId,
        Integer priority,
        BigDecimal price,
        String currency
) {
}
