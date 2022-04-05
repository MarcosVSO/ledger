package com.ledger.danos.service;

import com.ledger.danos.dtos.DanosAmbientaisListDTO;
import com.ledger.danos.dtos.DanosHumanosListDTO;
import com.ledger.danos.dtos.DanosMateriaisListDTO;
import com.ledger.danos.dtos.DanosMateriaisSomaDTO;
import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.entities.DanosMateriais;
import com.ledger.danos.repositories.DanosAmbientaisRepository;
import com.ledger.danos.repositories.DanosHumanosRepository;
import com.ledger.danos.repositories.DanosMateriaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DanosService {
    @Autowired
    private DanosMateriaisRepository danosMateriaisRepository;

    @Autowired
    private DanosAmbientaisRepository danosAmbientaisRepository;

    @Autowired
    private DanosHumanosRepository danosHumanosRepository;

    public List<DanosAmbientais> findAllDanosAmbientais(){
        return danosAmbientaisRepository.findAll();
    }

    public List<DanosHumanos> findAllDanosHumanos(){
        return danosHumanosRepository.findAll();
    }

    public List<DanosMateriais> findAllDanosMateriais(){
        return danosMateriaisRepository.findAll();
    }

    public Optional<DanosAmbientais> findDanosAmbientais(Integer id){
        return danosAmbientaisRepository.findById(id);
    }
    public Optional<DanosMateriais> findDanosMateriais(Integer id){
        return danosMateriaisRepository.findById(id);
    }
    public Optional<DanosHumanos> findDanosHumanos(Integer id){
        return danosHumanosRepository.findById(id);
    }

    public List<DanosAmbientaisListDTO> findAllDanosAmbientaisByOcorrencia(Integer idOcorrencia){
        return danosAmbientaisRepository.findAllDanosAmbientaisByOcorrencia(idOcorrencia);
    }

    public List<DanosHumanosListDTO> findAllDanosHumanosByOcorrencia(Integer idOcorrencia){
        return danosHumanosRepository.findAllDanosHumanosByOcorrencia(idOcorrencia);
    }

    public List<DanosMateriaisListDTO> findAllDanosMateriaisByOcorrencia(Integer idOcorrencia){
        return danosMateriaisRepository.findAllDanosMateriaisByOcorrencia(idOcorrencia);
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

    public Integer saveDanosMateriais(DanosMateriais dano) {
        return danosMateriaisRepository.save(dano).getId();
    }

    public Integer saveDanosAmbientais(DanosAmbientais dano) {
        return danosAmbientaisRepository.save(dano).getId();
    }

    public Integer saveDanosHumanos(DanosHumanos dano) {
        return danosHumanosRepository.save(dano).getId();
    }
    //TODO byId, insert, update, delete

}
