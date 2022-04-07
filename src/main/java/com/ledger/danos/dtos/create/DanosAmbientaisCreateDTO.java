package com.ledger.danos.dtos.create;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DanosAmbientaisCreateDTO {
    @NotNull
    private Integer ocorrenciaId;
    @NotNull
    private Integer danoAmbientalTipo;
    @NotNull
    private Integer populacaoAtingida;
    private String latitude;
    private String longitude;
    private String fotos;
}
