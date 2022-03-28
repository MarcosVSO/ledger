package com.ledger.cobrade;

import com.ledger.ocorrencia.entities.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CobradeRepository extends JpaRepository<Cobrade,Integer> {

    @Query("SELECT c FROM Cobrade c WHERE c.codigo = ?1")
    public Optional<Cobrade> findByCodigo(String cobrade);
}
