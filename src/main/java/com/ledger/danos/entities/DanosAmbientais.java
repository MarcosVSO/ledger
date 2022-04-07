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

    @Lob
    @Column(name="foto")
    private byte[] foto;

    @Column(name="latitude")
    private String latitude;

    @Column(name="longitude")
    private String longitude;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="ocorrencia_id")
    private Ocorrencia ocorrencia;

    @Column(name="dano_ambiental_tipo")
    private Integer danoAmbientalTipo;

    @Column(name="populacao_atingida")
    private Integer populacaoAtingida;
}
