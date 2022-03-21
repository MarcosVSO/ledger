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

    public ResponseEntity<List<Ocorrencia>> findAllByCobrade(String cobrade){
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAllByCobrade(cobrade);
        return new ResponseEntity<List<Ocorrencia>>(ocorrencias, HttpStatus.OK);
    }

    public ResponseEntity<List<Ocorrencia>> findAllByUf(String uf){
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAllByUf(uf);
        return new ResponseEntity<List<Ocorrencia>>(ocorrencias, HttpStatus.OK);
    }

    public ResponseEntity<List<Ocorrencia>> findAllByMunicipio(String municipio){
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAllByMunicipio(municipio);
        return new ResponseEntity<List<Ocorrencia>>(ocorrencias, HttpStatus.OK);
    }

    public ResponseEntity<String> save(Ocorrencia ocorrencia){
        ocorrenciaRepository.save(ocorrencia);
        return new ResponseEntity<String>("Ocorrência registrada com sucesso",HttpStatus.OK);
    }

}
