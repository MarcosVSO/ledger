package com.ledger.ocorrencia.dto;

import com.ledger.areasAfetadas.entities.AreaAfetada;
import com.ledger.cobrade.Cobrade;
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
    private Date dataOcorrencia;
    private Cobrade cobrade;
    private String uf;
    private String municipio;
}
