package com.ledger.danos.dtos;

import com.ledger.database.types.Coordenadas;
import lombok.Data;

import java.sql.Blob;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class DanoDTO {
    private Long id;
    private Set<Blob> fotos = new LinkedHashSet<>();
    private Coordenadas coordenadas;
    private Set<DanosAmbientaisDTO> ambientais = new LinkedHashSet<>();
    private Set<DanosHumanosDTO> humanos = new LinkedHashSet<>();
    private Set<DanosMateriaisDTO> materiais = new LinkedHashSet<>();
}
