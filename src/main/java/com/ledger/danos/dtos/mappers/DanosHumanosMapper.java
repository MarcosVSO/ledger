package com.ledger.danos.dtos.mappers;

import com.ledger.danos.dtos.DanosHumanosDTO;
import com.ledger.danos.dtos.create.DanosHumanosCreateDTO;
import com.ledger.danos.entities.DanosHumanos;
import org.mapstruct.Mapper;

@Mapper
public interface DanosHumanosMapper {
    DanosHumanosDTO toListDto(DanosHumanos danosHumanos);
    DanosHumanos fromCreatetoEntity(DanosHumanosCreateDTO danosHumanos);
}
