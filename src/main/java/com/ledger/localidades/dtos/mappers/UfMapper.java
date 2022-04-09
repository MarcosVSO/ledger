package com.ledger.localidades.dtos.mappers;

import com.ledger.localidades.dtos.UfDTO;
import com.ledger.localidades.entities.Uf;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UfMapper {
    Uf toEntity(UfDTO dto);
    List<Uf> toEntityList(List<UfDTO> dtoList);
    UfDTO toDTO(Uf entity);
    List<UfDTO> toDTOList(List<Uf> entityList);

    default String toString(Uf entity) {
        if (entity == null) {
            return  null;
        }
        return entity.getSigla();
    }
}
