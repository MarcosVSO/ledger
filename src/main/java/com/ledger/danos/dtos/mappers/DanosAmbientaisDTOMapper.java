package com.ledger.danos.dtos.mappers;

import com.ledger.danos.dtos.create.DanosAmbientaisCreateDTO;
import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.ocorrencia.entities.Ocorrencia;

public class DanosAmbientaisDTOMapper {
    public static DanosAmbientais toEntity(DanosAmbientaisCreateDTO dto) {
        var ocorrencia = new Ocorrencia();
        ocorrencia.setId(dto.getOcorrenciaId());
        return DanosAmbientais.builder()
                .id(null)
                .danoAmbientalTipo(dto.getDanoAmbientalTipo())
                .fotos(dto.getFotos())
                .ocorrencia(ocorrencia)
                .populacaoAtingida(dto.getPopulacaoAtingida())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();
    }
}
