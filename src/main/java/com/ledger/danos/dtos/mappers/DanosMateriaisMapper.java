package com.ledger.danos.dtos.mappers;

import com.ledger.danos.dtos.DanosMateriaisDTO;
import com.ledger.danos.dtos.create.DanosMateriaisCreateDTO;
import com.ledger.danos.entities.Dano;
import com.ledger.danos.entities.DanosMateriais;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {TipoMapper.class, DanoMapper.class})
public interface DanosMateriaisMapper {
    DanosMateriaisDTO toListDto(DanosMateriais danosMateriais);

    DanosMateriais fromCreatetoEntity(DanosMateriaisCreateDTO danosMateriais);

}
