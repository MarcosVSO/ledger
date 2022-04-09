package com.ledger.cobrade.repository;

import com.ledger.cobrade.entities.Cobrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CobradeRepository extends JpaRepository<Cobrade,Integer> {

    @Query("SELECT c FROM Cobrade c WHERE c.codigo = ?1")
    public Optional<Cobrade> findByCodigo(String cobrade);
}
