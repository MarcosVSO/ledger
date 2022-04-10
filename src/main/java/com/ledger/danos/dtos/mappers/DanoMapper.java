package com.ledger.danos.dtos.mappers;

import com.ledger.danos.dtos.DanoDTO;
import com.ledger.danos.entities.Dano;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Named("DanoMapper")
@Mapper(uses = {DanosAmbientaisMapper.class, DanosHumanosMapper.class, DanosMateriaisMapper.class, TipoMapper.class})
public interface DanoMapper {

    DanoDTO toDTO(Dano dano);

    @Named("toDtoWithoutChildren")
    @Mapping(target = "ambientais", ignore = true)
    @Mapping(target = "humanos", ignore = true)
    @Mapping(target = "materiais", ignore = true)
    DanoDTO toDtoWithoutChildren(Dano dano);

    Dano toEntity(DanoDTO dano);

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
