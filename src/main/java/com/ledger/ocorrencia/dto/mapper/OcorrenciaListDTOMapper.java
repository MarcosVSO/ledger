package com.ledger.ocorrencia.dto.mapper;

import com.ledger.ocorrencia.dto.OcorrenciaDetailsDTO;
import com.ledger.ocorrencia.dto.OcorrenciaListDTO;
import com.ledger.ocorrencia.entities.Ocorrencia;

public class OcorrenciaListDTOMapper {
    public static OcorrenciaListDTO toDTO(Ocorrencia o) {
        return OcorrenciaListDTO.builder()
                .id(o.getId())
                .cobrade(o.getCobrade())
                .dataOcorrencia(o.getDataOcorrencia())
                .municipio(o.getMunicipio())
                .uf(o.getUf())
                .build();
    }
}
