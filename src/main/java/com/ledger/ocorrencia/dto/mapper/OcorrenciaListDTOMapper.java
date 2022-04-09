package com.ledger.ocorrencia.dto.mapper;

import com.ledger.localidades.service.LocalidadeService;
import com.ledger.ocorrencia.dto.OcorrenciaListDTO;
import com.ledger.ocorrencia.entities.Ocorrencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OcorrenciaListDTOMapper {
    @Autowired
    private LocalidadeService localidadeService;

    public OcorrenciaListDTO toDTO(Ocorrencia o) {
        var builder =
                OcorrenciaListDTO.builder().id(o.getId()).data(o.getData());

//        EstadoDTO e = localidadeService.findEstadoById(o.getUf());
//        if (e != null) {
//            builder.uf(e.getSigla());
//        }
//
//        MunicipioDTO m = localidadeService.findMunicipioById(o.getMunicipio());
//        if (m != null) {
//            builder.municipio(m.getNome());
//        }

        return builder.build();
    }
}
