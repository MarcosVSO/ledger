package com.ledger.danos.rest;


import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.entities.tipos.DanosAmbientaisTipo;
import com.ledger.danos.entities.tipos.DanosHumanosTipo;
import com.ledger.danos.entities.tipos.DanosMateriaisTipo;
import com.ledger.danos.service.DanosTiposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/danos-tipos")
public class DanosTiposRest {

    @Autowired
    private DanosTiposService danosTiposService;

    @GetMapping("/humanos")
    public ResponseEntity<List<DanosHumanosTipo>> findAllDanoHumanosTipos(){
        List<DanosHumanosTipo> danosHumanosList = danosTiposService.findAllDanoHumanoTipo();
        return new ResponseEntity<List<DanosHumanosTipo>>(danosHumanosList, HttpStatus.OK);
    }

    @GetMapping("/materiais")
    public ResponseEntity<List<DanosMateriaisTipo>> findAllDanoMateriaisTipos(){
        List<DanosMateriaisTipo> danosMateriaisTipos = danosTiposService.findAllDanoMaterialTipo();
        return new ResponseEntity<List<DanosMateriaisTipo>>(danosMateriaisTipos, HttpStatus.OK);
    }

    @GetMapping("/ambientais")
    public ResponseEntity<List<DanosAmbientaisTipo>> findAllDanoAmbientaisTipos(){
        List<DanosAmbientaisTipo> danosAmbientaisTipos = danosTiposService.findAllDanoAmbientalTipo();
        return new ResponseEntity<List<DanosAmbientaisTipo>>(danosAmbientaisTipos, HttpStatus.OK);
    }


}
