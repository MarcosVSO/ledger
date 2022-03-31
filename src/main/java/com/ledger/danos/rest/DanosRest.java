package com.ledger.danos.rest;

import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.entities.DanosMateriais;
import com.ledger.danos.service.DanosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/danos")
public class DanosRest {
    @Autowired
    private DanosService danosService;

    @GetMapping("/ambientais")
    public ResponseEntity<List<DanosAmbientais>> findAllDanosAmbientais() {
        return danosService.findAllDanosAmbientais();
    }

    @GetMapping("/humanos")
    public ResponseEntity<List<DanosHumanos>> findAllDanosHumanos() {
        return danosService.findAllDanosHumanos();
    }

    @GetMapping("/materiais")
    public ResponseEntity<List<DanosMateriais>> findAllDanosMateriais() {
        return danosService.findAllDanosMateriais();
    }

    @PostMapping("/humanos/add")
    public ResponseEntity addDanosHumanos(@RequestBody DanosHumanos danosHumanos){
        danosService.addDanosHumanos(danosHumanos);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/materiais/add")
    public ResponseEntity addDanosMateriais(@RequestBody DanosMateriais danosMateriais){
        danosService.addDanosMateriais(danosMateriais);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/ambientais/add")
    public ResponseEntity addDanosAmbientais(@RequestBody DanosAmbientais danosAmbientais){
        danosService.addDanosAmbientais(danosAmbientais);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
