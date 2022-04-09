package com.ledger.danos.dtos.mappers;

import com.ledger.danos.dtos.DanosAmbientaisDTO;
import com.ledger.danos.dtos.create.DanosAmbientaisCreateDTO;
import com.ledger.danos.entities.DanosAmbientais;
import org.mapstruct.Mapper;

@Mapper
public interface DanosAmbientaisMapper {
    DanosAmbientaisDTO toListDto(DanosAmbientais danosAmbientais);
    DanosAmbientais fromCreatetoEntity(DanosAmbientaisCreateDTO danosAmbientais);
}
