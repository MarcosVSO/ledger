package com.ledger.ocorrencia.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ledger.areasAfetadas.entities.AreaAfetada;
import com.ledger.cobrade.Cobrade;
import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.entities.DanosMateriais;
import com.ledger.telefone.entities.Telefone;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ocorrencia")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cod_cobrade", referencedColumnName = "id")
    private Cobrade cobrade;

    @Column(name="data_ocorrencia")
    private Date dataOcorrencia;

    @Column(name="latitude")
    private String latitude;

    @Column(name="longitude")
    private String longitude;

    @Column(name="municipio")
    private String municipio;

    @Column(name="uf")
    private String uf;

    @Column(name="instituicao_informante_nome")
    private String instInformanteNome;

    @Column(name="instituicao_informante_responsavel")
    private String instInformanteResponsavel;

    @OneToMany(mappedBy="ocorrencia")
    @JsonManagedReference
    private List<Telefone> instInformanteTelefones;

    @Column(name="inst_informada_orgao_estadual_defesa_civil")
    private Boolean instInformadaOrgaoEstadual;

    @Column(name="inst_informada_sedec")
    private Boolean instituicaoInformadaSedec;

    @OneToMany(mappedBy = "ocorrencia")
    @JsonManagedReference
    private List<DanosAmbientais> danosAmbientais;

    @OneToMany(mappedBy = "ocorrencia")
    @JsonManagedReference
    private List<DanosHumanos> danosHumanos;

    @OneToMany(mappedBy = "ocorrencia")
    @JsonManagedReference
    private List<DanosMateriais> danosMateriais;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "area_afetada_id", referencedColumnName = "id")
    private AreaAfetada areaAfetada;

}
