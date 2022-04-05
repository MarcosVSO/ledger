package com.ledger.danos.repositories;

import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.dtos.DanosAmbientaisListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DanosAmbientaisRepository extends JpaRepository<DanosAmbientais, Integer> {

    @Query(
            value = "SELECT ISNULL(SUM(POPULACAO_ATINGIDA), 0)\n" +
                    "FROM DANOS_AMBIENTAIS da\n" +
                    "JOIN DANOS_TIPO dt ON dt.ID = da.DANO_AMBIENTAL_TIPO\n" +
                    "WHERE da.OCORRENCIA_ID = :idOcorrencia AND da.DANO_AMBIENTAL_TIPO = :danoTipo",
            nativeQuery = true)
    Integer getSomaDanosAmbientais(@Param("danoTipo") Integer danoTipo, @Param("idOcorrencia") Integer idOcorrencia);

    @Query(value = "SELECT d FROM DanosAmbientais d WHERE d.ocorrencia.id = :idOcorrencia")
    List<DanosAmbientaisListDTO> findAllDanosAmbientaisByOcorrencia(@Param("idOcorrencia") Integer idOcorrencia);
}
