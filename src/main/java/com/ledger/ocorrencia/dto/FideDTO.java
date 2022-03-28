package com.ledger.ocorrencia.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.entities.DanosMateriais;
import com.ledger.telefones.entities.Telefone;
import lombok.Data;


import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

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
    private List<DanosAmbientais> danosAmbientais;
    private List<DanosHumanos> danosHumanos;
    private List<DanosMateriais> danosMateriais;
}
