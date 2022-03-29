package com.ledger.danos.repositories;

import com.ledger.danos.entities.DanosHumanos;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface DanosHumanosRepository extends JpaRepository<DanosHumanos,Integer> {

}
