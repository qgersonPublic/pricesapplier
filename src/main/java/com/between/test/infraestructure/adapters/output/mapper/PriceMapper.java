package com.between.test.infraestructure.adapters.output.mapper;

import com.between.test.domain.dto.PricesDto;
import com.between.test.domain.model.Prices;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PriceMapper {
    PricesDto mapTo(Prices prices);
}
