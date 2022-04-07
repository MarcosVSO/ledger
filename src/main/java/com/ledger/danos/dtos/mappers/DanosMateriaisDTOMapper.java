package com.ledger.danos.dtos.mappers;

import com.ledger.danos.dtos.create.DanosMateriaisCreateDTO;
import com.ledger.danos.entities.DanosMateriais;
import com.ledger.ocorrencia.entities.Ocorrencia;

public class DanosMateriaisDTOMapper {
    public static DanosMateriais toEntity(DanosMateriaisCreateDTO dto) {
        var ocorrencia = new Ocorrencia();
        ocorrencia.setId(dto.getOcorrenciaId());
        return DanosMateriais.builder()
                .ocorrencia(ocorrencia)
                .danoMaterialTipo(dto.getDanoMaterialTipo())
                .quantidadeDestruida(dto.getQuantidadeDestruida())
                .quantidadeDanificada(dto.getQuantidadeDanificada())
                .valor(dto.getValor())
                .foto(dto.getFoto())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();
    }
}
