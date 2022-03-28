package com.ledger.danos.rest;

import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.entities.DanosMateriais;
import com.ledger.danos.service.DanosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
