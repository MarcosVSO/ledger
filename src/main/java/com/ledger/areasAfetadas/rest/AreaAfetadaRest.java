package com.ledger.areasAfetadas.rest;

import com.ledger.areasAfetadas.service.AreaAfetadaService;
import com.ledger.areasAfetadas.entities.AreaAfetada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/areas-afetadas")
public class AreaAfetadaRest {

    @Autowired
    private AreaAfetadaService areaAfetadaService;

    @GetMapping
    public ResponseEntity<List<AreaAfetada>> findAll(){
        return areaAfetadaService.findAll();
    }
}
