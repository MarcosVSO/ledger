package com.ledger.telefone.service;

import com.ledger.telefone.entities.Telefone;
import com.ledger.telefone.repositories.TelefoneRepository;
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

    public void deleteByOcorrenciaId(Integer ocorrenciaId) {
        telefoneRepository.deleteAllByOcorrencia_Id(ocorrenciaId);
    }

    public void salvar(Telefone telefone) {
        telefoneRepository.save(telefone);
    }
}
