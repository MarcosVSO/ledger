package com.ledger.ocorrencia.dto.mapper;

import com.ledger.cobrade.Cobrade;
import com.ledger.ocorrencia.dto.OcorrenciaDetailsDTO;
import com.ledger.ocorrencia.dto.SalvarOcorrenciaDTO;
import com.ledger.ocorrencia.entities.Ocorrencia;

public class SalvarOcorrenciaDTOMapper {
    public static Ocorrencia toEntity(SalvarOcorrenciaDTO o) {
        var cobrade = new Cobrade();
        cobrade.setCodigo(o.getCodCobrade());
        return Ocorrencia.builder()
                .id(o.getId())
                .cobrade(cobrade)
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
