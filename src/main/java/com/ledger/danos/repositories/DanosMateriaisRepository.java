package com.ledger.danos.repositories;

import com.ledger.danos.dtos.DanosMateriaisSomaDTO;
import com.ledger.danos.entities.DanosMateriais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DanosMateriaisRepository extends JpaRepository<DanosMateriais, Integer> {

    @Query(
            value = "select\n" +
                    "(select dt2.descricao  as danoMaterialTipo\n" +
                    "from dano_tipos dt2\n" +
                    "where dt2.id = :danoTipo),\n"+
                    "Count(dm.id) as quantidadeDanificada,\n" +
                    "coalesce (SUM(dm.valor),\n" +
                    "0) as valorDanificado,\n" +
                    "(\n" +
                    "select\n" +
                    "Count(dm.id) as quantidadeDestruida\n" +
                    "from\n" +
                    "danos_materiais dm\n" +
                    "right join dano_tipos dt on\n" +
                    "dt.id = dm.tipo_id\n" +
                    "right join danos d on\n" +
                    "dm.dano_id = d.id\n" +
                    "where\n" +
                    "d.ocorrencia_id = :idOcorrencia\n" +
                    "and dm.tipo_id = :danoTipo\n" +
                    "and dm.destruido = true ),\n" +
                    "(\n" +
                    "select\n" +
                    "coalesce (SUM(dm.valor),\n" +
                    "0) as valorDestruido\n" +
                    "from\n" +
                    "danos_materiais dm\n" +
                    "right join dano_tipos dt on\n" +
                    "dt.id = dm.tipo_id\n" +
                    "right join danos d on\n" +
                    "dm.dano_id = d.id\n" +
                    "where\n" +
                    "d.ocorrencia_id = :idOcorrencia\n" +
                    "and dm.tipo_id = :danoTipo\n" +
                    "and dm.destruido = true)\n" +
                    "from\n" +
                    "danos_materiais dm\n" +
                    "right join dano_tipos dt on\n" +
                    "dt.id = dm.tipo_id\n" +
                    "right join danos d on\n" +
                    "dm.dano_id = d.id\n" +
                    "where\n" +
                    "d.ocorrencia_id = :idOcorrencia\n" +
                    "and dm.tipo_id = :danoTipo\n" +
                    "and dm.destruido = false\n",
            nativeQuery = true)
    DanosMateriaisSomaDTO getSomaDanosMateriais(@Param("danoTipo") Integer danoTipo, @Param("idOcorrencia") Integer idOcorrencia);

    @Query("SELECT d FROM DanosMateriais d WHERE d.dano.id = :id")
    List<DanosMateriais> findAllDanosMateriaisByDanoId(@Param("id") Long id);
}
