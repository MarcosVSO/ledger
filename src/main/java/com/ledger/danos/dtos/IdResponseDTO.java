package com.ledger.danos.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class IdResponseDTO {
    private Integer id;

    public static IdResponseDTO from(Integer id) {
        var dto = new IdResponseDTO();
        dto.setId(id);
        return dto;
    }
}
