package com.ledger.ocorrencia.dto.mapper;

import com.ledger.localidades.dtos.EstadoDTO;
import com.ledger.localidades.dtos.MunicipioDTO;
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
                .areaAfetada(o.getAreaAfetada());

        EstadoDTO e = localidadeService.findEstadoById(o.getUf());
        if (e != null) {
            builder.uf(e.getSigla());
        }

        MunicipioDTO m = localidadeService.findMunicipioById(o.getMunicipio());
        if (m != null) {
            builder.municipio(m.getNome());
        }

        return builder.build();
    }
}
