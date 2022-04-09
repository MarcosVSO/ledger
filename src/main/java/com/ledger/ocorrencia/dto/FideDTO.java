package com.ledger.ocorrencia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ledger.ocorrencia.entities.AreaAfetada;
import com.ledger.danos.dtos.DanosMateriaisSomaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FideDTO {

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
    @JsonProperty("areas_afetadas")
    private AreaAfetada areaAfetada;

    @JsonProperty("danos_ambientais")
    private Map<String, Integer> danosAmbientaisMapped = new HashMap<String, Integer>();
    @JsonProperty("danos_humanos")
    private Map<String, Integer> danosHumanosMapped = new HashMap<String, Integer>();
    @JsonProperty("danos_materiais")
    private List<DanosMateriaisSomaDTO> danosMateriaisSoma = new LinkedList<DanosMateriaisSomaDTO>();

    public String getInstInformadaOrgaoEstadual(){
        return this.instInformadaOrgaoEstadual ? "S" : "N";
    }

    public String getInstituicaoInformanteSedec(){
        return this.instituicaoInformadaSedec ? "S" : "N";
    }

}
