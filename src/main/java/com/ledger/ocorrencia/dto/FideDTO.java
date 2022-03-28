package com.ledger.ocorrencia.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.entities.DanosMateriais;
import com.ledger.telefones.entities.Telefone;
import lombok.Data;


import javax.persistence.OneToMany;
import java.lang.reflect.Array;
import java.util.*;

@Data
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
    private List<Telefone> instInformanteTelefones;
    private Boolean instInformadaOrgaoEstadual;
    private Boolean instituicaoInformadaSedec;

    @JsonProperty("danos_ambientais")
    private Map<String, Integer> danosAmbientaisMapped = new HashMap<String, Integer>();
    @JsonProperty("danos_humanos")
    private Map<String, Integer> danosHumanosMapped = new HashMap<String, Integer>();
    @JsonProperty("danos_materiais")
    private List<DanosMateriais> danosMateriais = new ArrayList<DanosMateriais>();



}
