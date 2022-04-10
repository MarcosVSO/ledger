package com.ledger.danos.dtos.mappers;

import com.ledger.danos.dtos.DanoCreateDTO;
import com.ledger.danos.dtos.DanoDetailsDTO;
import com.ledger.danos.entities.Dano;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = FotoMapper.class)
public interface DanoMapper {
    @Mapping(target = "fotos", ignore = true)
    @Mapping(target = "latitude", source = "coordenadas.latitude")
    @Mapping(target = "longitude", source = "coordenadas.longitude")
    DanoCreateDTO toSimpleDTO(Dano dano);

    @Mapping(target = "latitude", source = "coordenadas.latitude")
    @Mapping(target = "longitude", source = "coordenadas.longitude")
    DanoDetailsDTO toDTOWithImages(Dano dano);

    @Mapping(target = "coordenadas.latitude", source = "latitude")
    @Mapping(target = "coordenadas.longitude", source = "longitude")
    Dano toEntity(DanoCreateDTO dano);

    default Long toId(Dano d) {
        if (d == null) {
            return null;
        }

        return d.getId();
    }

    default Dano toEntity(Long id) {
        var t = new Dano();
        t.setId(id);
        return t;
    }
}
