package com.ledger.ocorrencia.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="area_afetada")
public class AreaAfetada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "residencial")
    private String residencial;

    @Column(name = "comercial")
    private String comercial;

    @Column(name = "industrial")
    private String industrial;

    @Column(name = "agricola")
    private String agricola;

    @Column(name = "pecuaria")
    private String pecuaria;

    @Column(name = "extrativismo_vegetal")
    private String extrativismoVegetal;

    @Column(name = "reserva_florestal")
    private String reservaFlorestal;

    @Column(name = "mineracao")
    private String mineracao;

    @Column(name = "turismo_outras")
    private String turismoOutras;

    @OneToOne(optional = false)
    @JoinColumn(name = "ocorrencia_id", nullable = false)
    private Ocorrencia ocorrencia;

}



