package com.ledger.rest.dto.mappers;

import com.ledger.rest.dto.IdResponseDTO;
import org.mapstruct.Mapper;

@Mapper
public interface IdResponseMapper {
    default IdResponseDTO toDto(Integer id) {
        var dto = new IdResponseDTO();
        dto.setId(id.longValue());
        return dto;
    }

    default IdResponseDTO toDto(Long id) {
        var dto = new IdResponseDTO();
        dto.setId(id);
        return dto;
    }
}
