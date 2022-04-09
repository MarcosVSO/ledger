package com.ledger.cobrade.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="cobrade")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cobrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="categoria")
    private String categoria;

    @Column(name="grupo")
    private String grupo;

    @Column(name="subgrupo")
    private String subGrupo;

    @Column(name="tipo")
    private String tipo;

    @Column(name="subtipo")
    private String subTipo;

    @Column(name="definicao")
    private String definicao;

    @Column(name="codigo")
    private String codigo;
}
