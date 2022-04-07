package com.ledger.localidades.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoDTO {
    private Integer id;
    private String sigla;
    private String nome;
}
