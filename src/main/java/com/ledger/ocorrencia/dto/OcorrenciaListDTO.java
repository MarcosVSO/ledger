package com.ledger.ocorrencia.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class OcorrenciaListDTO {
    private Integer id;
    private String codCobrade;
    private Date dataOcorrencia;
    private String municipio;
    private String uf;
    }
