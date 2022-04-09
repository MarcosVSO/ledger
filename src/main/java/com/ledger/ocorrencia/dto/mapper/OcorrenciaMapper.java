package com.ledger.ocorrencia.dto.mapper;

import com.ledger.cobrade.dto.mapper.CobradeMapper;
import com.ledger.localidades.dtos.mappers.MunicipioMapper;
import com.ledger.ocorrencia.dto.OcorrenciaDTO;
import com.ledger.ocorrencia.dto.ListOcorrenciaDTO;
import com.ledger.ocorrencia.entities.Ocorrencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {CobradeMapper.class, MunicipioMapper.class})
public interface OcorrenciaMapper {

    ListOcorrenciaDTO toListDTO(Ocorrencia entity);

    @Mapping(target = "latitude", source = "coordenadas.latitude")
    @Mapping(target = "longitude", source = "coordenadas.longitude")
    @Mapping(target = "codCobrade", source = "cobrade.codigo")
    OcorrenciaDTO toDetailsDTO(Ocorrencia entity);

    @Mapping(target = "coordenadas.latitude", source = "latitude")
    @Mapping(target = "coordenadas.longitude", source = "longitude")
    @Mapping(target = "cobrade.codigo", source = "codCobrade")
    @Mapping(target = "municipio.id", source = "municipio")
    Ocorrencia fromCreateToEntity(OcorrenciaDTO dto);
}
