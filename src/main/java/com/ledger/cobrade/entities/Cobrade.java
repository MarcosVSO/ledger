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
    @Column(name="codigo")
    private String codigo;

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
}
