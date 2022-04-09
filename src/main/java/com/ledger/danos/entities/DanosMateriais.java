package com.ledger.danos.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "danos_materiais")
public class DanosMateriais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @JsonIgnore
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_id", nullable = false)
    private Tipo tipo;

    @Column(name="destruido")
    private Boolean destruido;

    @Column(name="descricao")
    private String descricao;

    @Column(name="valor")
    private BigInteger valor;

    @ManyToOne
    @JoinColumn(name = "dano_id")
    private Dano dano;
}
