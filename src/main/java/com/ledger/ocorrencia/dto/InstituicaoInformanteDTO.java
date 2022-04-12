package com.ledger.ocorrencia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstituicaoInformanteDTO {
    private Long id;
    @NotNull
    private Integer ocorrencia;
    @NotNull
    private String nome;
    @NotNull
    private String responsavel;
    @NotNull
    private List<String> telefones = new ArrayList<>();

    public String getTelefonesString(){
        return this.telefones.stream()
                .map(s -> s+"\n")
                .collect(Collectors.joining());
    }
}
