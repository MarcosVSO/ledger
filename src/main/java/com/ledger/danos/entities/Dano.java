package com.ledger.danos.entities;

import com.ledger.database.types.Coordenadas;
import com.ledger.ocorrencia.entities.Ocorrencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;
import java.util.LinkedHashSet;
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

    @Lob
    @ElementCollection(fetch = FetchType.LAZY)
    @Column(name = "foto")
    @CollectionTable(name = "danos_fotos", joinColumns = @JoinColumn(name = "dano_id"))
    private Set<Blob> fotos = new LinkedHashSet<>();

    @Embedded
    private Coordenadas coordenadas;

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
