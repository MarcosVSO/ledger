package com.ledger.ocorrencia.repositories;

import com.ledger.ocorrencia.entities.AreaAfetada;
import com.ledger.ocorrencia.entities.InstituicaoInformante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituicaoInformanteRepository extends JpaRepository<InstituicaoInformante, Integer> {
}
