package com.ledger.areasAfetadas.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ledger.ocorrencia.entities.Ocorrencia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="area_afetada")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AreaAfetada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="residencial")
    private String residencial;

    @Column(name="comercial")
    private String comercial;

    @Column(name="industrial")
    private String industrial;

    @Column(name="agricola")
    private String agricola;

    @Column(name="pecuaria")
    private String pecuaria;

    @Column(name="extrativismo_vegetal")
    private String extrativismoVegetal;

    @Column(name="reserva_florestal")
    private String reservaFlorestal;

    @Column(name="mineracao")
    private String mineracao;




}

