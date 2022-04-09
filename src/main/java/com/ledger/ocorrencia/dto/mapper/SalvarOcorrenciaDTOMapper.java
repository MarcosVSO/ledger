package com.ledger.ocorrencia.dto.mapper;

import com.ledger.ocorrencia.dto.OcorrenciaDetailsDTO;
import com.ledger.ocorrencia.dto.SalvarOcorrenciaDTO;
import com.ledger.ocorrencia.entities.Ocorrencia;

public class SalvarOcorrenciaDTOMapper {
    public static Ocorrencia toEntity(SalvarOcorrenciaDTO o) {
        return Ocorrencia.builder()
                .id(o.getId())
                .data(o.getDataOcorrencia())
                .municipio(o.getMunicipio())
                .uf(o.getUf())
                .dcInformada(o.getInstInformadaOrgaoEstadual())
                .sedecInformado(o.getInstituicaoInformadaSedec())
                .areaAfetada(o.getAreaAfetada())
                .build();
    }
}
