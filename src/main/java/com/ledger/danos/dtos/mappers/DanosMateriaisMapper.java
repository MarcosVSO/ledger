package com.ledger.danos.dtos.mappers;

import com.ledger.danos.dtos.DanosMateriaisDTO;
import com.ledger.danos.dtos.create.DanosMateriaisCreateDTO;
import com.ledger.danos.entities.Dano;
import com.ledger.danos.entities.DanosMateriais;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {TipoMapper.class, DanoMapper.class})
public interface DanosMateriaisMapper {
    @Mapping(target = "tipo", source = "tipo.id")
    DanosMateriaisDTO toListDto(DanosMateriais danosMateriais);

    @Mapping(target = "tipo.id", source = "tipo")
    DanosMateriais fromCreatetoEntity(DanosMateriaisCreateDTO danosMateriais);

}
