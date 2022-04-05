package com.ledger.danos.repositories;

import com.ledger.danos.dtos.DanosHumanosListDTO;
import com.ledger.danos.entities.DanosHumanos;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DanosHumanosRepository extends JpaRepository<DanosHumanos, Integer> {

    @Query(
            value = "SELECT ISNULL(SUM(NUMERO_PESSOAS), 0)\n" +
                    "FROM DANOS_HUMANOS dh\n" +
                    "JOIN DANOS_TIPO dt ON dt.ID = dh.DANO_HUMANO_TIPO\n" +
                    "WHERE dh.OCORRENCIA_ID = :idOcorrencia AND dh.DANO_HUMANO_TIPO = :danoTipo",
            nativeQuery = true)
    Integer getSomaDanosHumanos(@Param("danoTipo") Integer danoTipo, @Param("idOcorrencia") Integer idOcorrencia);

    @Query(value = "SELECT d FROM DanosHumanos d WHERE d.ocorrencia.id = :idOcorrencia")
    List<DanosHumanosListDTO> findAllDanosHumanosByOcorrencia(@Param("idOcorrencia") Integer idOcorrencia);
}
