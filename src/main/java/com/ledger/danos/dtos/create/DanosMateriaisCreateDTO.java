package com.ledger.danos.dtos.create;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Getter
@Setter
public class DanosMateriaisCreateDTO {
    @NotNull
    private Integer ocorrenciaId;
    @NotNull
    private Integer danoMaterialTipo;
    @NotNull
    private Integer quantidadeDanificada;
    @NotNull
    private Integer quantidadeDestruida;
    @NotNull
    private BigInteger valor;
    private String latitude;
    private String longitude;
    private byte[] foto;
}
