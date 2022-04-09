package com.ledger.danos.dtos.mappers;

import com.ledger.danos.dtos.DanosMateriaisDTO;
import com.ledger.danos.dtos.create.DanosMateriaisCreateDTO;
import com.ledger.danos.entities.DanosMateriais;
import org.mapstruct.Mapper;

@Mapper
public interface DanosMateriaisMapper {
    DanosMateriaisDTO toListDto(DanosMateriais danosMateriais);
    DanosMateriais fromCreatetoEntity(DanosMateriaisCreateDTO danosMateriais);

}
