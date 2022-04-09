package com.ledger.danos.dtos.create;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DanosAmbientaisCreateDTO {
    @NotNull
    private Integer tipo;
    @NotNull
    private Integer populacaoAtingida;
}
