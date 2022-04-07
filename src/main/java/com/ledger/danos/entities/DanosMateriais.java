package com.ledger.danos.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ledger.ocorrencia.entities.Ocorrencia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "danos_materiais")
public class DanosMateriais implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @JsonIgnore
    private Integer id;

    @Lob
    @Column(name="fotos")
    private String fotos;

    @Column(name="latitude")
    private String latitude;

    @Column(name="longitude")
    private String longitude;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="ocorrencia_id")
    private Ocorrencia ocorrencia;

    @Column(name="dano_material_tipo")
    private Integer danoMaterialTipo;

    @Column(name="quantidade_destruida")
    private Integer quantidadeDestruida;

    @Column(name="quantidade_danificada")
    private Integer quantidadeDanificada;

    @Column(name="valor")
    private BigInteger valor;
}
