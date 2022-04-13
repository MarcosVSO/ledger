package com.ledger.danos.repositories;

import com.ledger.danos.entities.Dano;
import com.ledger.danos.entities.DanosMateriais;
import com.ledger.danos.entities.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FotosRepository extends JpaRepository<Dano, Long> {
    @Query("SELECT f FROM Foto f WHERE f.id = :id ")
    Optional<Foto> findFotoById(@Param("id") Long fotoId);
}
