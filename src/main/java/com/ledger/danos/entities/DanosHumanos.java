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
@Table(name = "danos_humanos")
public class DanosHumanos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_id", nullable = false)
    private Tipo tipo;

    @Column(name="numero_pessoas")
    private Integer numeroPessoas;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dano_id", nullable = false)
    private Dano dano;
}
