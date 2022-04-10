package com.ledger.danos.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DanosAmbientaisDTO {
    private Integer id;
    private Integer tipo;
    private Integer populacaoAtingida;
}
