package com.ledger.cobrade.dto.mapper;

import com.ledger.cobrade.dto.CobradeDTO;
import com.ledger.cobrade.entities.Cobrade;
import org.mapstruct.Mapper;

@Mapper
public interface CobradeMapper {
    CobradeDTO cobradeToCobradeDto(Cobrade cobrade);
    Cobrade cobradeDtoToCobrade(CobradeDTO cobrade);
    default String toString(Cobrade cobrade){
        if (cobrade == null) {
            return null;
        }

        var builder = new StringBuilder();
        if (cobrade.getSubGrupo() != null) {
            builder.append(cobrade.getSubGrupo());
        }

        if (cobrade.getTipo() != null) {
            builder.append(" - ").append(cobrade.getTipo());
        }

        if (cobrade.getSubTipo() != null) {
            builder.append(" - ").append(cobrade.getSubTipo());
        }

        return builder.toString();
    }
}
