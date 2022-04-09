package com.ledger.ocorrencia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AreaAfetadaDTO {
    private Integer id;
    @NotNull
    private Integer ocorrenciaId;
    @NotNull
    private String residencial;
    @NotNull
    private String comercial;
    @NotNull
    private String industrial;
    @NotNull
    private String agricola;
    @NotNull
    private String pecuaria;
    @NotNull
    private String extrativismoVegetal;
    @NotNull
    private String reservaFlorestal;
    @NotNull
    private String mineracao;
    @NotNull
    private String turismoOutras;

}



