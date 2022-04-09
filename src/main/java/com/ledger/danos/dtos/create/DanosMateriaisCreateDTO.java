package com.ledger.danos.dtos.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
