package com.ledger.ocorrencia.dto.mapper;

import com.ledger.ocorrencia.dto.OcorrenciaDetailsDTO;
import com.ledger.ocorrencia.entities.Ocorrencia;

public class OcorrenciaDetailsDTOMapper {
    public static OcorrenciaDetailsDTO toDTO(Ocorrencia o) {
        return OcorrenciaDetailsDTO.builder()
                .id(o.getId())
                .codCobrade(o.getCodCobrade())
                .dataOcorrencia(o.getDataOcorrencia())
                .latitude(o.getLatitude())
                .longitude(o.getLongitude())
                .municipio(o.getMunicipio())
                .uf(o.getUf())
                .instInformanteNome(o.getInstInformanteNome())
                .instInformanteResponsavel(o.getInstInformanteResponsavel())
                .instInformanteTelefones(o.getInstInformanteTelefones())
                .instInformadaOrgaoEstadual(o.getInstInformadaOrgaoEstadual())
                .instituicaoInformadaSedec(o.getInstituicaoInformadaSedec())
                .areaAfetada(o.getAreaAfetada())
                .build();
    }
}
