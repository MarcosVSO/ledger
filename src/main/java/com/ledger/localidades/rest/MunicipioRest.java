package com.ledger.localidades.rest;

import com.ledger.localidades.dtos.MunicipioDTO;
import com.ledger.localidades.dtos.mappers.MunicipioMapper;
import com.ledger.localidades.entities.Municipio;
import com.ledger.localidades.service.LocalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/municipios")
public class MunicipioRest {
    private final LocalidadeService localidadeService;
    private final MunicipioMapper municipioMapper;

    @Autowired
    public MunicipioRest(LocalidadeService localidadeService, MunicipioMapper municipioMapper) {
        this.localidadeService = localidadeService;
        this.municipioMapper = municipioMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MunicipioDTO> findMunicipioById (@PathVariable("id") Integer id) {
        Optional<Municipio> municipioOptional = localidadeService.findMunicipioById(id);
        if (municipioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(municipioMapper.toDTO(municipioOptional.get()));
    }
}
