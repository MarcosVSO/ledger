package com.ledger.localidades.dtos.mappers;

import com.ledger.localidades.dtos.MunicipioDTO;
import com.ledger.localidades.entities.Municipio;
import org.mapstruct.Mapper;

@Mapper
public interface MunicipioMapper {
    Municipio toEntity(MunicipioDTO dto);
    MunicipioDTO toDTO(Municipio entity);
    default String toString(Municipio entity) {
        if (entity == null) {
            return null;
        }

        return entity.getUf().getSigla() + " - "  + entity.getNome();
    }
}
