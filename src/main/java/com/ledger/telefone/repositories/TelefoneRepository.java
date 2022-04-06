package com.ledger.telefone.repositories;

import com.ledger.telefone.entities.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone,Integer> {
    void deleteAllByOcorrencia_Id(Integer ocorrenciaId);
}
