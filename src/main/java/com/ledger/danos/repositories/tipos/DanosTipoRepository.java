package com.ledger.danos.repositories.tipos;

import com.ledger.danos.entities.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DanosTipoRepository extends JpaRepository<Tipo,Integer> {
    List<Tipo> findAllByCategoria(String categoria);
}
