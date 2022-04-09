package com.ledger.danos.rest;


import com.ledger.danos.entities.Tipo;
import com.ledger.danos.service.DanosTiposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/danos-tipos")
public class DanosTiposRest {

    @Autowired
    private DanosTiposService danosTiposService;

    @GetMapping()
    public ResponseEntity<List<Tipo>> getByCategoria(@RequestParam("categoria") String categoria){
        List<Tipo> tipos = danosTiposService.findAllDanoTipoByCategoria(categoria);
        return new ResponseEntity(tipos, HttpStatus.OK);
    }

}
