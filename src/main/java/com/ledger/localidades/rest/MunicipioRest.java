package com.ledger.localidades.rest;

import com.ledger.localidades.dtos.EstadoDTO;
import com.ledger.localidades.dtos.MunicipioDTO;
import com.ledger.localidades.service.LocalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/municipios")
public class MunicipioRest {
    @Autowired
    LocalidadeService localidadeService;

    @GetMapping("/{id}")
    public ResponseEntity<MunicipioDTO> findMunicipioById (@PathVariable("id") String id){
        return new ResponseEntity<>(localidadeService.findMunicipioById(id), HttpStatus.OK);
    }
}
