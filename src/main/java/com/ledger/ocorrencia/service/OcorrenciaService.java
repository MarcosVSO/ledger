package com.ledger.ocorrencia.service;

import com.ledger.ocorrencia.entities.Ocorrencia;
import com.ledger.ocorrencia.repositories.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcorrenciaService {
    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    public ResponseEntity<List<Ocorrencia>> findAll(){
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAll();
        return new ResponseEntity<List<Ocorrencia>>(ocorrencias, HttpStatus.OK);
    }
}
