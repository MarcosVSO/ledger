package com.ledger.danos.dtos;

import com.ledger.danos.entities.Dano;
import lombok.Data;

import java.math.BigInteger;

@Data
public class DanosMateriaisDTO {
    private Integer id;
    private Integer tipo;
    private Boolean destruido;
    private Boolean descricao;
    private BigInteger valor;
    private Dano dano;
}
