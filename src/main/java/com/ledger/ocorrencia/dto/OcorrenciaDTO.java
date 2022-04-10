package com.ledger.ocorrencia.dto;

import com.ledger.cobrade.dto.CobradeDTO;
import com.ledger.localidades.dtos.MunicipioDTO;
import com.ledger.localidades.dtos.UfDTO;
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
    private CobradeDTO cobrade;
    private Date data  = new Date();
    private String latitude;
    private String longitude;
    private UfDTO uf;
    private MunicipioDTO municipio;
    private Boolean dcInformada = false;
    private Boolean sedecInformado = false;
    private InstituicaoInformanteDTO instituicaoInformante;
    private AreaAfetadaDTO areaAfetada;
}
