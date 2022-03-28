package com.ledger.danos.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ledger.ocorrencia.entities.Ocorrencia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "danos_ambientais")
public class DanosAmbientais{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="foto")
    private Blob foto;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="ocorrencia_id")
    private Ocorrencia ocorrencia;

    @Column(name="dano_ambiental_tipo")
    private String danoAmbientalTipo;

    @Column(name="populacao_atingida")
    private Integer populacaoAtingida;
}
