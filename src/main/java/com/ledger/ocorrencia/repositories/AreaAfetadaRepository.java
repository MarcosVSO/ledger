package com.ledger.ocorrencia.repositories;

import com.ledger.ocorrencia.entities.AreaAfetada;
import com.ledger.ocorrencia.entities.Ocorrencia;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AreaAfetadaRepository extends JpaRepository<AreaAfetada,Integer> {
}
