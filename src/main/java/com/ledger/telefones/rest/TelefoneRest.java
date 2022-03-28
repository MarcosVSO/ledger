package com.ledger.telefones.rest;

import com.ledger.telefones.entities.Telefone;
import com.ledger.telefones.service.TelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/telefones")
public class TelefoneRest {

    @Autowired
    private TelefoneService telefoneService;

    @GetMapping
    public ResponseEntity<List<Telefone>> findAll() {
        return telefoneService.findAll();
    }

    //TODO byId, insert, update, delete
}
