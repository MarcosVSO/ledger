package com.ledger.danos.repositories;

import com.ledger.danos.entities.DanosHumanos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DanosHumanosRepository extends JpaRepository<DanosHumanos, Integer> {

    @Query(
            value = "select count(dh.id)\n" +
                    "from danos_humanos dh\n" +
                    "join danos d on d.id = dh.dano_id\n" +
                    "where d.ocorrencia_id = :idOcorrencia and dh.tipo_id = :danoTipo\n",
            nativeQuery = true)
    Integer getSomaDanosHumanos(@Param("danoTipo") Integer danoTipo, @Param("idOcorrencia") Integer idOcorrencia);

    @Query("SELECT d FROM DanosHumanos d WHERE d.dano.id = :id")
    List<DanosHumanos> findAllDanosHumanosByDanoId(@Param("id") Long id);
}
