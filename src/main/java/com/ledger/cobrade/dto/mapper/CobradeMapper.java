package com.ledger.cobrade.dto.mapper;

import com.ledger.cobrade.dto.CobradeDTO;
import com.ledger.cobrade.entities.Cobrade;
import org.mapstruct.Mapper;

@Mapper
public interface CobradeMapper {
    CobradeDTO cobradeToCobradeDto(Cobrade cobrade);
    Cobrade cobradeDtoToCobrade(CobradeDTO cobrade);
}
