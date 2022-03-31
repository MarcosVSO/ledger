package com.ledger.ocorrencia.repositories;

import com.ledger.ocorrencia.entities.Ocorrencia;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia,Integer> {

    @Query("SELECT o FROM Ocorrencia o WHERE o.codCobrade = ?1")
    public List<Ocorrencia> findAllByCobrade(String cobrade);

    @Query("SELECT o FROM Ocorrencia o WHERE o.uf = ?1")
    public List<Ocorrencia> findAllByUf(String uf);

    @Query("SELECT o FROM Ocorrencia o WHERE o.municipio = ?1")
    public List<Ocorrencia> findAllByMunicipio(String municipio);

    @Query("SELECT o FROM Ocorrencia o WHERE o.codCobrade = :cobrade or :cobrade is null")
    Slice<Ocorrencia> findAllByCodCobrade(@Param("cobrade") String  cobrade, Pageable page);
}
