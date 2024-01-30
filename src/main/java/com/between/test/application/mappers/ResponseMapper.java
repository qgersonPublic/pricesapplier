package com.between.test.application.mappers;

import com.between.test.domain.dto.PriceResponseDto;
import com.between.test.domain.dto.PricesDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ResponseMapper {

    PriceResponseDto mapTo(PricesDto dto);
}
