package com.ledger.danos.dtos.mappers;

import com.ledger.danos.dtos.create.DanosHumanosCreateDTO;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.ocorrencia.entities.Ocorrencia;

public class DanosHumanosDTOMapper {
    public static DanosHumanos toEntity(DanosHumanosCreateDTO dto) {
        var ocorrencia = new Ocorrencia();
        ocorrencia.setId(dto.getOcorrenciaId());
        return DanosHumanos.builder()
                .ocorrencia(ocorrencia)
                .danoHumanoTipo(dto.getDanoHumanoTipo())
                .numeroPessoas(dto.getNumeroPessoas())
                .fotos(dto.getFotos())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();
    }
}
