package com.ledger.danos.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DanosHumanosDTO {
    private Integer id;
    private Integer tipo;
    private Integer numeroPessoas;
}
