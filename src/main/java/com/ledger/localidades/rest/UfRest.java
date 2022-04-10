package com.ledger.localidades.rest;

import com.ledger.localidades.dtos.UfDTO;
import com.ledger.localidades.dtos.MunicipioDTO;
import com.ledger.localidades.dtos.mappers.MunicipioMapper;
import com.ledger.localidades.dtos.mappers.UfMapper;
import com.ledger.localidades.service.LocalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class UfRest {
    private final LocalidadeService localidadeService;
    private final UfMapper ufMapper;
    private final MunicipioMapper municipioMapper;

    @Autowired
    public UfRest(LocalidadeService localidadeService, UfMapper ufMapper, MunicipioMapper municipioMapper) {
        this.localidadeService = localidadeService;
        this.ufMapper = ufMapper;
        this.municipioMapper = municipioMapper;
    }

    @GetMapping()
    public ResponseEntity<List<UfDTO>> findAllEstados() {
        return ResponseEntity.ok(ufMapper.toDTOList(localidadeService.findAllEstados()));
    }

    @GetMapping("/{id}/municipios")
    public ResponseEntity<List<MunicipioDTO>> findMunicipiosByUF(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(localidadeService.findMunicipiosByUF(id).stream().map(municipioMapper::toDTO).collect(Collectors.toList()));
    }
}
