package com.ps.warehouse.mappers;

import com.ps.warehouse.dtos.DealDetail;
import com.ps.warehouse.entities.DealDetailEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DealDetailMapper {

    DealDetail entityToDto(DealDetailEntity dealDetailEntity);

    DealDetailEntity dtoTOEntity(DealDetail dealDetailDto);
}
