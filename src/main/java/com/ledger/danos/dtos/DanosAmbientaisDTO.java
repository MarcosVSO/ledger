package com.ledger.danos.dtos;

import com.ledger.danos.entities.Dano;
import lombok.Data;

@Data
public class DanosAmbientaisDTO {
    private Integer id;
    private Integer tipo;
    private Integer populacaoAtingida;
    private Dano dano;
}
