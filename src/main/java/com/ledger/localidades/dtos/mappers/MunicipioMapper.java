package com.ledger.localidades.dtos.mappers;

import com.ledger.localidades.dtos.MunicipioDTO;
import com.ledger.localidades.entities.Municipio;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MunicipioMapper {
    Municipio toEntity(MunicipioDTO dto);
    List<Municipio> toEntityList(List<MunicipioDTO> dtoList);
    MunicipioDTO toDTO(Municipio entity);
    List<MunicipioDTO> toDTOList(List<Municipio> entityList);
}
