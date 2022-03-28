package com.ledger.telefones.repositories;

import com.ledger.telefones.entities.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone,Integer> {
}
