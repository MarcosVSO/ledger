package com.ledger.cobrade.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CobradeDTO {
    private String codigo;
    private String categoria;
    private String grupo;
    private String subGrupo;
    private String tipo;
    private String subTipo;
    private String definicao;
}
