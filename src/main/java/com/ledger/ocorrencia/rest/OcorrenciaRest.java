package com.ledger.ocorrencia.rest;

import com.ledger.ocorrencia.entities.Ocorrencia;
import com.ledger.ocorrencia.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/ocorrencias")
public class OcorrenciaRest {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @GetMapping
    public ResponseEntity<List<Ocorrencia>> sayHello() {
        return ocorrenciaService.findAll();
    }
}
