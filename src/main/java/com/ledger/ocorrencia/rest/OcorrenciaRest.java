package com.ledger.ocorrencia.rest;

import com.ledger.ocorrencia.entities.Ocorrencia;
import com.ledger.ocorrencia.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/ocorrencias")
public class OcorrenciaRest {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @GetMapping
    public ResponseEntity<List<Ocorrencia>> findAll() {
        return ocorrenciaService.findAll();
    }

    @GetMapping("/cobrade/{cobrade}")
    public ResponseEntity<List<Ocorrencia>> findAllByCobrade(@PathVariable String cobrade) {
        return ocorrenciaService.findAllByCobrade(cobrade);
    }

    @GetMapping("/uf/{uf}")
    public ResponseEntity<List<Ocorrencia>> findAllByUf(@PathVariable String uf) {
        return ocorrenciaService.findAllByUf(uf);
    }

    @GetMapping("/municipio/{municipio}")
    public ResponseEntity<List<Ocorrencia>> findAllByMunicipio(@PathVariable String municipio) {
        return ocorrenciaService.findAllByMunicipio(municipio);
    }

    @PostMapping("/add")
    public ResponseEntity<String> findAllByMunicipio(@RequestBody Ocorrencia ocorrencia) {
        return ocorrenciaService.save(ocorrencia);
    }


}
