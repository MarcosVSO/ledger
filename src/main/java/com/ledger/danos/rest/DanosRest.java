package com.ledger.danos.rest;

import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.entities.DanosMateriais;
import com.ledger.danos.service.DanosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/danos")
public class DanosRest {
    @Autowired
    private DanosService danosService;

    @GetMapping("/ambientais")
    public ResponseEntity<List<DanosAmbientais>> findAllDanosAmbientais() {
        var response = danosService.findAllDanosAmbientais();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/humanos")
    public ResponseEntity<List<DanosHumanos>> findAllDanosHumanos() {
        var response = danosService.findAllDanosHumanos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/materiais")
    public ResponseEntity<List<DanosMateriais>> findAllDanosMateriais() {
        var response = danosService.findAllDanosMateriais();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ambientais/{idDano}")
    public ResponseEntity<DanosAmbientais> findDanosAmbientais(@PathVariable Integer idDano) {
        Optional<DanosAmbientais> danosAmbientais = danosService.findDanosAmbientais(idDano);
        return ResponseEntity.of(danosAmbientais);
    }

    @GetMapping("/humanos/{idDano}")
    public ResponseEntity<DanosHumanos> findDanosHumanos(@PathVariable Integer idDano) {
        Optional<DanosHumanos> danosHumanos = danosService.findDanosHumanos(idDano);
        return ResponseEntity.of(danosHumanos);
    }

    @GetMapping("/materiais/{idDano}")
    public ResponseEntity<DanosMateriais> findDanosMateriais(@PathVariable Integer idDano) {
        Optional<DanosMateriais> danosMateriais = danosService.findDanosMateriais(idDano);
        return ResponseEntity.of(danosMateriais);
    }

}
