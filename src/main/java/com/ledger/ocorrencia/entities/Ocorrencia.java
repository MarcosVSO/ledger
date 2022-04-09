package com.ledger.ocorrencia.entities;

import com.ledger.cobrade.entities.Cobrade;
import com.ledger.danos.entities.Dano;
import com.ledger.database.types.Coordenadas;
import com.ledger.localidades.entities.Municipio;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ocorrencia")
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data")
    private Date data;

    @Embedded
    private Coordenadas coordenadas;

    @Column(name = "defesa_civil_informada")
    private Boolean dcInformada = false;

    @Column(name = "sedec_informado")
    private Boolean sedecInformado = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "municipio_id", nullable = false)
    private Municipio municipio;

    @OneToOne(mappedBy = "ocorrencia", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private InstituicaoInformante instituicaoInformante;

    @OneToOne(mappedBy = "ocorrencia", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private AreaAfetada areaAfetada;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cod_cobrade", nullable = false)
    private Cobrade cobrade;

    @OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dano> danos = new ArrayList<>();


}
