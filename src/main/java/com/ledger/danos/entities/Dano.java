package com.ledger.danos.entities;

import com.ledger.database.types.Coordenadas;
import com.ledger.ocorrencia.entities.Ocorrencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "danos")
public class Dano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long  id;

    @OneToMany(mappedBy = "dano", cascade = CascadeType.ALL)
    private List<Foto> fotos = new ArrayList<>();

    @Embedded
    private Coordenadas coordenadas;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ocorrencia_id", nullable = false)
    private Ocorrencia ocorrencia;

    @OneToMany(mappedBy = "dano", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DanosAmbientais> ambientais = new LinkedHashSet<>();

    @OneToMany(mappedBy = "dano", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DanosHumanos> humanos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "dano", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DanosMateriais> materiais = new LinkedHashSet<>();


}
