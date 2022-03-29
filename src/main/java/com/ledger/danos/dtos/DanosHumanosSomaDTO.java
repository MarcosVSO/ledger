package com.ledger.danos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DanosHumanosSomaDTO {
    private String danoHumanoTipo;
    private Integer totalPessoas;
    private String descricao;
}
