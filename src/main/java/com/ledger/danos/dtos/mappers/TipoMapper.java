package com.ledger.danos.dtos.mappers;

import com.ledger.danos.entities.Tipo;
import org.mapstruct.Mapper;

@Mapper
public interface TipoMapper {

    default Integer toId(Tipo t) {
        if (t == null) {
            return null;
        }

        return t.getId();
    }

    default Tipo toEntity(Integer id) {
        var t = new Tipo();
        t.setId(id);
        return t;
    }
}
