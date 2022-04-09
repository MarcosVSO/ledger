package com.ledger.ocorrencia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OcorrenciaDTO {
    private Integer id;
    private String codCobrade;
    private Date data  = new Date();
    private String latitude;
    private String longitude;
    private String municipio;
    private Boolean dcInformada = false;
    private Boolean sedecInformado = false;
    private InstituicaoInformanteDTO instituicaoInformante;
    private AreaAfetadaDTO areaAfetada;
}
