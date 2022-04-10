package com.ledger.ocorrencia.repositories;

import com.ledger.ocorrencia.entities.InstituicaoInformante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstituicaoInformanteRepository extends JpaRepository<InstituicaoInformante, Long> {
    Optional<InstituicaoInformante> findByOcorrencia_Id(Integer id);
}
