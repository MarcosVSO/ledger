package com.ledger.ocorrencia.dto.mapper;

import com.ledger.localidades.service.LocalidadeService;
import com.ledger.ocorrencia.dto.OcorrenciaDetailsDTO;
import com.ledger.ocorrencia.entities.Ocorrencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OcorrenciaDetailsDTOMapper {
    @Autowired
    private LocalidadeService localidadeService;

    public OcorrenciaDetailsDTO toDTO(Ocorrencia o) {
        var builder =  OcorrenciaDetailsDTO.builder()
                .id(o.getId())
                .dataOcorrencia(o.getData())
                .municipio(o.getMunicipio())
                .uf(o.getUf())
                .instInformadaOrgaoEstadual(o.getDcInformada())
                .instituicaoInformadaSedec(o.getSedecInformado())
                .areaAfetada(o.getAreaAfetada());

        return builder.build();
    }
}
