package com.ledger.ocorrencia.dto;

import com.ledger.areasAfetadas.entities.AreaAfetada;
import com.ledger.telefone.entities.Telefone;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalvarOcorrenciaDTO {
    private Integer id;
    @NotNull
    private String codCobrade;
    @NotNull
    private Date dataOcorrencia;
    private String latitude;
    private String longitude;
    @NotNull
    private String municipio;
    @NotNull
    private String uf;
    @NotNull
    private AreaAfetada areaAfetada;
    private String instInformanteNome;
    private String instInformanteResponsavel;
    private List<Telefone> instInformanteTelefones;
    private Boolean instInformadaOrgaoEstadual;
    private Boolean instituicaoInformadaSedec;
}
