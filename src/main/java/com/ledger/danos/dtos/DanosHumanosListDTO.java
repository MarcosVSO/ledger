package com.ledger.danos.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ledger.ocorrencia.entities.Ocorrencia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Base64;

public interface DanosHumanosListDTO {
    Integer getId();

    Integer getDanoHumanoTipo();

    Integer getNumeroPessoas();

    String getFotos();
}
