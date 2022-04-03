package com.ledger.cobrade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value="/cobrade")
public class CobradeRest {

    @Autowired
    CobradeService cobradeService;

    @GetMapping
    public ResponseEntity<List<Cobrade>> findAll(){
        return cobradeService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cobrade> findById(@PathVariable Integer id){
        return cobradeService.findById(id);
    }

    @GetMapping("/cod/{codigoCobrade}")
    public ResponseEntity<Cobrade> findById(@PathVariable String codigoCobrade){
        return new ResponseEntity<Cobrade>(cobradeService.findByCodigo(codigoCobrade), HttpStatus.OK);
    }

    //TODO insert, update, delete

}
