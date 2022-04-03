package com.ledger.cobrade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CobradeService {
    @Autowired
    CobradeRepository cobradeRepository;

    public ResponseEntity<List<Cobrade>> findAll(){
        List<Cobrade> listaCobrade = cobradeRepository.findAll();
        return new ResponseEntity<List<Cobrade>>(listaCobrade, HttpStatus.OK);
    }

    public ResponseEntity<Cobrade> findById(Integer id){
        Optional<Cobrade> cobrade = cobradeRepository.findById(id);
        return new ResponseEntity<Cobrade>(cobrade.get(), HttpStatus.OK);
    }

    public Cobrade findByCodigo(String codigoCobrade){
        Optional<Cobrade> cobrade = cobradeRepository.findByCodigo(codigoCobrade);
        return cobrade.get();
    }
}
