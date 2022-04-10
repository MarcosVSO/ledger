package com.ledger.ocorrencia.repositories;

import com.ledger.ocorrencia.entities.AreaAfetada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaAfetadaRepository extends JpaRepository<AreaAfetada,Integer> {
    Optional<AreaAfetada> findByOcorrencia_Id(Integer id);
}
