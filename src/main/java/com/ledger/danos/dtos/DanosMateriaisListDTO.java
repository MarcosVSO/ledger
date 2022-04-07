package com.ledger.danos.dtos;

import java.util.Base64;

public interface DanosMateriaisListDTO {
    Integer getId();

    Integer getDanoMaterialTipo();

    Integer getQuantidadeDanificada();

    Integer getQuantidadeDestruida();

    Double getValor();

    String getFotos();
}
