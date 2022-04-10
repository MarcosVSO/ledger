package com.ledger.danos.dtos.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DanosAmbientaisCreateDTO {
    @NotNull
    private Integer tipo;
    @NotNull
    private Integer populacaoAtingida;
    @NotNull
    private Long dano;
}
