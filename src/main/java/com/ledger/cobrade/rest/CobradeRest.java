package com.ledger.cobrade.rest;

import com.ledger.cobrade.dto.mapper.CobradeMapper;
import com.ledger.cobrade.entities.Cobrade;
import com.ledger.cobrade.service.CobradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/cobrade")
public class CobradeRest {
    private final CobradeService cobradeService;
    private final CobradeMapper cobradeMapper;

    @Autowired
    public CobradeRest(CobradeService cobradeService, CobradeMapper cobradeMapper) {
        this.cobradeService = cobradeService;
        this.cobradeMapper = cobradeMapper;
    }

    @GetMapping
    public ResponseEntity<List<Cobrade>> findAll(){
        return cobradeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cobrade> findById(@PathVariable Integer id){
        return cobradeService.findById(id);
    }

    @GetMapping("/cod/{codigoCobrade}")
    public ResponseEntity<Cobrade> findByCodigo(@PathVariable String codigoCobrade){
        cobradeMapper.cobradeToCobradeDto(cobradeService.findByCodigo(codigoCobrade));
        return ResponseEntity.ok(cobradeService.findByCodigo(codigoCobrade));
    }

    //TODO insert, update, delete

}
