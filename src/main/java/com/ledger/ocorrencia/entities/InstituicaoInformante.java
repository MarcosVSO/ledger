package com.ledger.ocorrencia.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instituicao_informante")
public class InstituicaoInformante {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE, optional = false, orphanRemoval = true)
    @JoinColumn(name = "ocorrencia_id", nullable = false)
    private Ocorrencia ocorrencia;

    @Column(name="nome")
    private String nome;

    @Column(name="responsavel")
    private String responsavel;

    @ElementCollection
    @Column(name = "telefone", length = 20)
    @CollectionTable(name = "instituicao_informante_telefones")
    private List<String> telefones = new ArrayList<>();


}
