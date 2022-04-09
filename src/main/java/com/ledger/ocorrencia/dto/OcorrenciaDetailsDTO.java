package com.ledger.ocorrencia.dto;

import com.ledger.areasAfetadas.entities.AreaAfetada;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class OcorrenciaDetailsDTO {
    private Integer id;
    private String codCobrade;
    private Date dataOcorrencia;
    private String latitude;
    private String longitude;
    private String municipio;
    private String uf;
    private String instInformanteNome;
    private String instInformanteResponsavel;
    private Boolean instInformadaOrgaoEstadual;
    private Boolean instituicaoInformadaSedec;
    private AreaAfetada areaAfetada;
}
