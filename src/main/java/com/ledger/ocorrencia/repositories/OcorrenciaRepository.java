package com.ledger.ocorrencia.repositories;

import com.ledger.ocorrencia.entities.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia,Integer> {
}
