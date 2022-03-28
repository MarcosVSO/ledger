package com.ledger.danos.service;

import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.entities.DanosMateriais;
import com.ledger.danos.repositories.DanosAmbientaisRepository;
import com.ledger.danos.repositories.DanosHumanosRepository;
import com.ledger.danos.repositories.DanosMateriaisRepository;
import com.ledger.ocorrencia.entities.Ocorrencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanosService {
    @Autowired
    private DanosMateriaisRepository danosMateriaisRepository;

    @Autowired
    private DanosAmbientaisRepository danosAmbientaisRepository;

    @Autowired
    private DanosHumanosRepository danosHumanosRepository;

    public ResponseEntity<List<DanosAmbientais>> findAllDanosAmbientais(){
        List<DanosAmbientais> danosAmbientais = danosAmbientaisRepository.findAll();
        return new ResponseEntity<List<DanosAmbientais>>(danosAmbientais, HttpStatus.OK);
    }

    public ResponseEntity<List<DanosHumanos>> findAllDanosHumanos(){
        List<DanosHumanos> danosHumanos = danosHumanosRepository.findAll();
        return new ResponseEntity<List<DanosHumanos>>(danosHumanos, HttpStatus.OK);
    }

    public ResponseEntity<List<DanosMateriais>> findAllDanosMateriais(){
        List<DanosMateriais> danosMteriais = danosMateriaisRepository.findAll();
        return new ResponseEntity<List<DanosMateriais>>(danosMteriais, HttpStatus.OK);
    }

    //TODO byId, insert, update, delete

}
