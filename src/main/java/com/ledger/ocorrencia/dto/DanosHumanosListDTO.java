package com.ledger.ocorrencia.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ledger.ocorrencia.entities.Ocorrencia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

public interface DanosHumanosListDTO {
    Integer getId();

    Integer getDanoHumanoTipo();

    Integer getNumeroPessoas();
}
