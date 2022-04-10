package com.ledger.localidades.repository;

import com.ledger.localidades.entities.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
    @Query("SELECT m FROM Municipio m WHERE m.uf.id = :uf")
    List<Municipio> findAllByUfId(@Param("uf") Integer uf);
}
