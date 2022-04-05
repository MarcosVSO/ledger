package com.ledger.danos.repositories.tipos;

import com.ledger.danos.entities.tipos.DanoTipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DanosTipoRepository extends JpaRepository<DanoTipo,Integer> {
    List<DanoTipo> findAllByCategoria(String categoria);
}
