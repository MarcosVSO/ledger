package com.ledger.danos.dtos.create;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DanosHumanosCreateDTO {
    @NotNull
    private Integer ocorrenciaId;
    @NotNull
    private Integer danoHumanoTipo;
    @NotNull
    private Integer numeroPessoas;
    private String latitude;
    private String longitude;
    private byte[] foto;
}
