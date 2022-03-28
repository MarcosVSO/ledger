package com.ledger.areasAfetadas.service;

import com.ledger.areasAfetadas.entities.AreaAfetada;
import com.ledger.areasAfetadas.repositories.AreaAfetadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaAfetadaService {
    @Autowired
    private AreaAfetadaRepository areaAfetadaRepository;

    public ResponseEntity<List<AreaAfetada>> findAll(){
        List<AreaAfetada> areaAfetadas = areaAfetadaRepository.findAll();
        return new ResponseEntity<List<AreaAfetada>>(areaAfetadas, HttpStatus.OK);
    }
}
