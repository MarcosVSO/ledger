package com.ledger.danos.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ledger.danos.entities.Dano;
import com.ledger.ocorrencia.entities.Ocorrencia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Base64;

@Data
public class DanosHumanosDTO {
    private Integer id;
    private Integer tipo;
    private Integer numeroPessoas;
    private Dano dano;
}
