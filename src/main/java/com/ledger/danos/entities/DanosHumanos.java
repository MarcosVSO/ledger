package com.ledger.danos.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ledger.ocorrencia.entities.Ocorrencia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Base64;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "danos_humanos")
public class DanosHumanos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
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

    @Column(name="dano_humano_tipo")
    private Integer danoHumanoTipo;

    @Column(name="numero_pessoas")
    private Integer numeroPessoas;
}
