package com.ledger.danos.dtos;

import lombok.Data;

import java.math.BigInteger;

@Data
public class DanosMateriaisDTO {
    private Integer id;
    private Integer tipo;
    private Boolean destruido;
    private String descricao;
    private BigInteger valor;
}
