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
public class DanosHumanosCreateDTO {
    @NotNull
    private Integer tipo;
    @NotNull
    private Integer numeroPessoas;
    @NotNull
    private Long dano;
}
