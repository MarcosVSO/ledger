package com.ledger.danos.dtos.mappers;

import com.ledger.danos.dtos.DanosAmbientaisDTO;
import com.ledger.danos.dtos.create.DanosAmbientaisCreateDTO;
import com.ledger.danos.entities.Dano;
import com.ledger.danos.entities.DanosAmbientais;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses ={TipoMapper.class})
public interface DanosAmbientaisMapper {
    @Mapping(target = "tipo", source = "tipo.id")
    DanosAmbientaisDTO toListDto(DanosAmbientais danosAmbientais);

    @Mapping(target = "tipo.id", source = "tipo")
    DanosAmbientais fromCreatetoEntity(DanosAmbientaisCreateDTO danosAmbientais);

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
