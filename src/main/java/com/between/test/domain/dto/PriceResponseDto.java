package com.between.test.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PriceResponseDto(
        Long productId,
        Integer brandId,
        Integer priceList,
        @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime startDate,
        @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime endDate,
        BigDecimal price
) {
}
