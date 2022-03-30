package com.ledger.danos.repositories;

import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.entities.DanosHumanos;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DanosHumanosRepository extends JpaRepository<DanosHumanos,Integer> {

    @Query(
            value = "SELECT ISNULL(SUM(NUMERO_PESSOAS), 0)\n" +
                    "FROM DANOS_HUMANOS dh\n" +
                    "JOIN DANOS_HUMANOS_TIPO dht ON dht.ID = dh.DANO_HUMANO_TIPO\n" +
                    "WHERE dh.OCORRENCIA_ID = :danoTipo AND dh.DANO_HUMANO_TIPO = :idOcorrencia",
            nativeQuery = true)
        Integer getSomaDanosHumanos(@Param("danoTipo") Integer danoTipo, @Param("idOcorrencia")  Integer idOcorrencia);

    @Query(value = "SELECT d FROM DanosHumanos d WHERE d.ocorrencia.id = :idOcorrencia")
    List<DanosHumanos> findAllDanosHumanosByOcorrencia(@Param("idOcorrencia") Integer idOcorrencia);
}
