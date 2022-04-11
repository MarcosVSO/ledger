package com.ledger.danos.dtos.mappers;

import com.ledger.danos.dtos.DanosHumanosDTO;
import com.ledger.danos.dtos.create.DanosHumanosCreateDTO;
import com.ledger.danos.entities.Dano;
import com.ledger.danos.entities.DanosHumanos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {TipoMapper.class, DanoMapper.class})
public interface DanosHumanosMapper {
    DanosHumanosDTO toListDto(DanosHumanos danosHumanos);

    DanosHumanos fromCreatetoEntity(DanosHumanosCreateDTO danosHumanos);
}
