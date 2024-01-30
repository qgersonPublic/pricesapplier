package com.between.test.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "PRICES")
public class Prices {

   @Id
   private Long id;

   @Column("BRAND_ID")
   private Integer brandId;

   @Column("START_DATE")
   private LocalDateTime startDate;

   @Column("END_DATE")
   private LocalDateTime endDate;

   @Column("PRICE_LIST")
   private Integer priceList;

   @Column("PRODUCT_ID")
   private Long productId;

   @Column("PRIORITY")
   private Integer priority;

   @Column("PRICE")
   private BigDecimal price;

   @Column("CURRENCY")
   private String currency;
}
