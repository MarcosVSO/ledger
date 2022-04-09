package com.ledger.danos.dtos.mappers;

import com.ledger.danos.dtos.DanoDTO;
import com.ledger.danos.entities.Dano;
import org.mapstruct.Mapper;

@Mapper(uses = {DanosAmbientaisMapper.class, DanosHumanosMapper.class, DanosMateriaisMapper.class})
public interface DanoMapper {
    DanoDTO toDTO(Dano dano);
    Dano toEntity(DanoDTO dano);
}
