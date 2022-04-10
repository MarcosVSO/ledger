package com.ledger.ocorrencia.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

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
    private OcorrenciaDTO dadosOcorrencia;

    @JsonProperty("danos_ambientais")
    private Map<String, Integer> danosAmbientaisMapped = new HashMap<String, Integer>();
    @JsonProperty("danos_humanos")
    private Map<String, Integer> danosHumanosMapped = new HashMap<String, Integer>();
    @JsonProperty("danos_materiais")
    private List<DanosMateriaisSomaDTO> danosMateriaisSoma = new LinkedList<DanosMateriaisSomaDTO>();

    public String getInstInformadaOrgaoEstadual(){
        return this.dadosOcorrencia.getDcInformada() ? "S" : "N";
    }

    public String getInstituicaoInformanteSedec(){
        return this.dadosOcorrencia.getSedecInformado() ? "S" : "N";
    }

}
