package com.ledger.danos.dtos.create;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
public class DanosMateriaisCreateDTO {
    @NotNull
    private Integer tipo;
    @NotNull
    private Boolean destruido;
    @NotNull
    private String descricao;
    @NotNull
    private BigInteger valor;
    @NotNull
    private Long dano;
}
