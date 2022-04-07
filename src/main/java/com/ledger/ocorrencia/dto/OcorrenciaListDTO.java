package com.ledger.ocorrencia.dto;

import com.ledger.areasAfetadas.entities.AreaAfetada;
import com.ledger.telefone.entities.Telefone;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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
