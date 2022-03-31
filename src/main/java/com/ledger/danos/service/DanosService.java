package com.ledger.danos.service;

import com.ledger.danos.dtos.DanosMateriaisSomaDTO;
import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.entities.DanosMateriais;
import com.ledger.danos.repositories.DanosAmbientaisRepository;
import com.ledger.danos.repositories.DanosHumanosRepository;
import com.ledger.danos.repositories.DanosMateriaisRepository;
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

    public ResponseEntity<List<DanosAmbientais>> findAllDanosAmbientaisByOcorrencia(Integer idOcorrencia){
        List<DanosAmbientais> danosAmbientais = danosAmbientaisRepository.findAllDanosAmbientaisByOcorrencia(idOcorrencia);
        return new ResponseEntity<List<DanosAmbientais>>(danosAmbientais, HttpStatus.OK);
    }

    public ResponseEntity<List<DanosHumanos>> findAllDanosHumanos(){
        List<DanosHumanos> danosHumanos = danosHumanosRepository.findAll();
        return new ResponseEntity<List<DanosHumanos>>(danosHumanos, HttpStatus.OK);
    }

    public ResponseEntity<List<DanosHumanos>> findAllDanosHumanosByOcorrencia(Integer idOcorrencia){
        List<DanosHumanos> danosHumanos = danosHumanosRepository.findAllDanosHumanosByOcorrencia(idOcorrencia);
        return new ResponseEntity<List<DanosHumanos>>(danosHumanos, HttpStatus.OK);
    }

    public ResponseEntity<List<DanosMateriais>> findAllDanosMateriais(){
        List<DanosMateriais> danosMteriais = danosMateriaisRepository.findAll();
        return new ResponseEntity<List<DanosMateriais>>(danosMteriais, HttpStatus.OK);
    }

    public ResponseEntity<List<DanosMateriais>> findAllDanosMateriaisByOcorrencia(Integer idOcorrencia){
        List<DanosMateriais> danosMteriais = danosMateriaisRepository.findAllDanosMateriaisByOcorrencia(idOcorrencia);
        return new ResponseEntity<List<DanosMateriais>>(danosMteriais, HttpStatus.OK);
    }

    public Integer getSomaDanosHumanos(Integer danoTipo, Integer idOcorrencia){
        return danosHumanosRepository.getSomaDanosHumanos(danoTipo,idOcorrencia);
    }

    public Integer getSomaDanosAmbientais(Integer danoTipo, Integer idOcorrencia){
        return danosAmbientaisRepository.getSomaDanosAmbientais(danoTipo,idOcorrencia);
    }

    public DanosMateriaisSomaDTO getSomaDanosMateriais(Integer danoTipo, Integer idOcorrencia, String tipoDano){
        return danosMateriaisRepository.getSomaDanosMateriais(danoTipo,idOcorrencia,tipoDano);
    }

    public void addDanosHumanos(DanosHumanos danosHumanos){
        danosHumanosRepository.save(danosHumanos);
    }

    public void addDanosMateriais(DanosMateriais danosMateriais){
        danosMateriaisRepository.save(danosMateriais);
    }

    public void addDanosAmbientais(DanosAmbientais danosAmbientais){
        danosAmbientaisRepository.save(danosAmbientais);
    }

    //TODO byId, insert, update, delete

}
