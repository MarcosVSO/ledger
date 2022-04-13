package com.ledger.danos.repositories;

import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.dtos.DanosAmbientaisDTO;
import com.ledger.danos.entities.DanosHumanos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DanosAmbientaisRepository extends JpaRepository<DanosAmbientais, Integer> {

    @Query(
            value = "select coalesce(SUM(POPULACAO_ATINGIDA), 0)\n" +
                    "FROM DANOS_AMBIENTAIS da\n" +
                    "JOIN DANO_TIPOS dt ON dt.ID = da.tipo_id\n" +
                    "join Danos d on da.dano_id = d.id\n" +
                    "where d.ocorrencia_id = :idOcorrencia and da.tipo_id = :danoTipo\n",
            nativeQuery = true)
    Integer getSomaDanosAmbientais(@Param("danoTipo") Integer danoTipo, @Param("idOcorrencia") Integer idOcorrencia);

    @Query("SELECT d FROM DanosAmbientais d WHERE d.dano.id = :id")
    List<DanosAmbientais> findAllDanosAmbientaisByDanoId(@Param("id") Long id);
}
