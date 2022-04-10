package com.ledger.danos.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DanosMateriaisDTO {
    private Integer id;
    private Integer tipo;
    private Boolean destruido;
    private String descricao;
    private BigInteger valor;
}
