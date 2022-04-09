package com.ledger.danos.dtos.create;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DanosHumanosCreateDTO {
    @NotNull
    private Integer tipo;
    @NotNull
    private Integer numeroPessoas;
    @NotNull
    private Long dano;
}
