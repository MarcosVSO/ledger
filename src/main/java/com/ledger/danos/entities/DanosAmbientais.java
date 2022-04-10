package com.ledger.danos.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ledger.ocorrencia.entities.Ocorrencia;
import lombok.*;

import javax.persistence.*;
import java.util.Base64;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "danos_ambientais")
public class DanosAmbientais{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_id", nullable = false)
    private Tipo tipo;

    @Column(name="populacao_atingida")
    private Integer populacaoAtingida;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dano_id", nullable = false)
    private Dano dano;

}
