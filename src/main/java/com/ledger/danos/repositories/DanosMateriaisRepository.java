package com.ledger.danos.repositories;

import com.ledger.danos.dtos.DanosMateriaisSomaDTO;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.entities.DanosMateriais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DanosMateriaisRepository extends JpaRepository<DanosMateriais,Integer> {

    @Query(
            value = "SELECT :danoMaterialTipo as danoMaterialTipo,ISNULL (SUM(QUANTIDADE_DANIFICADA),0) as quantidadeDanificada, ISNULL (SUM(QUANTIDADE_DESTRUIDA),0) as quantidadeDestruida, ISNULL (SUM(valor),0) as valor\n" +
                    "FROM DANOS_MATERIAIS dm\n" +
                    "JOIN DANOS_MATERIAIS_TIPO dmt ON dmt.ID = dm.DANO_MATERIAL_TIPO\n" +
                    "WHERE dm.OCORRENCIA_ID = :idOcorrencia AND dm.DANO_MATERIAL_TIPO = :danoTipo",
            nativeQuery = true)
    DanosMateriaisSomaDTO getSomaDanosMateriais(@Param("danoTipo") Integer danoTipo, @Param("idOcorrencia")  Integer idOcorrencia, @Param("danoMaterialTipo") String danoMaterialTipo);

    @Query(value = "SELECT d FROM DanosMateriais d WHERE d.ocorrencia.id = :idOcorrencia")
    List<DanosMateriais> findAllDanosMateriaisByOcorrencia(@Param("idOcorrencia") Integer idOcorrencia);
}
