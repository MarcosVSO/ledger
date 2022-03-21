package com.ledger.ocorrencia.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ocorrencia")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="id_inst_informante")
    private Integer idInstInformante;

    @Column(name="id_inst_informadas")
    private Integer idInstInformada;

    @Column(name="cod_cobrade")
    private String idCobrade;

    @Column(name="data_ocorrencia")
    private Date dataOcorrencia;

    @Column(name="latitude")
    private String latitude;

    @Column(name="longitude")
    private String longitude;

    @Column(name="municipio")
    private String municipio;

    @Column(name="uf")
    private String uf;

}
