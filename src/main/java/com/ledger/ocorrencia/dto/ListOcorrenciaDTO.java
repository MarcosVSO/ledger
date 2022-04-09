package com.ledger.ocorrencia.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ListOcorrenciaDTO {
    private Integer id;
    private String cobrade;
    private Date data;
    private String municipio;
    private String uf;
}
