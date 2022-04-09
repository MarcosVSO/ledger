package com.ledger.localidades.repository;

import com.ledger.localidades.entities.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
    List<Municipio> findAllByUf(String uf);
}
