package com.ledger.ocorrencia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstituicaoInformanteDTO {
    private Long id;
    @NotNull
    private Integer ocorrenciaId;
    @NotNull
    private String nome;
    @NotNull
    private String responsavel;
    @NotNull
    private List<String> telefones = new ArrayList<>();
}
