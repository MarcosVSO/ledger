package com.ledger.telefones.service;

import com.ledger.ocorrencia.entities.Ocorrencia;
import com.ledger.telefones.entities.Telefone;
import com.ledger.telefones.repositories.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    public ResponseEntity<List<Telefone>> findAll(){
        List<Telefone> telefones = telefoneRepository.findAll();
        return new ResponseEntity<List<Telefone>>(telefones, HttpStatus.OK);
    }
}
