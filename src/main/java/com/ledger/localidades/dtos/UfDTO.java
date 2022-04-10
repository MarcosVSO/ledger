package com.ledger.localidades.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UfDTO {
    private Integer id;
    private String sigla;
    private String nome;
}
