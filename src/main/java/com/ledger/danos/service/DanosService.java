package com.ledger.danos.service;

import com.ledger.danos.dtos.DanosAmbientaisDTO;
import com.ledger.danos.dtos.DanosHumanosDTO;
import com.ledger.danos.dtos.DanosMateriaisDTO;
import com.ledger.danos.dtos.DanosMateriaisSomaDTO;
import com.ledger.danos.entities.*;
import com.ledger.danos.repositories.DanosAmbientaisRepository;
import com.ledger.danos.repositories.DanosHumanosRepository;
import com.ledger.danos.repositories.DanosMateriaisRepository;
import com.ledger.danos.repositories.DanosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DanosService {
    private DanosMateriaisRepository danosMateriaisRepository;
    private DanosAmbientaisRepository danosAmbientaisRepository;
    private DanosHumanosRepository danosHumanosRepository;
    private DanosRepository danosRepository;

    public DanosService(DanosMateriaisRepository danosMateriaisRepository,
                        DanosAmbientaisRepository danosAmbientaisRepository,
                        DanosHumanosRepository danosHumanosRepository, DanosRepository danosRepository) {
        this.danosMateriaisRepository = danosMateriaisRepository;
        this.danosAmbientaisRepository = danosAmbientaisRepository;
        this.danosHumanosRepository = danosHumanosRepository;
        this.danosRepository = danosRepository;
    }

    public List<Dano> findDanosByOcorrenciaId(Integer ocorrenciaId) {
        return danosRepository.findAllByOcorrencia_Id(ocorrenciaId);
    }

    public Optional<Dano> findDanoById(Long id) {
        return danosRepository.findById(id);
    }

    public Optional<Foto> findFotoById(Long id) {
        return danosRepository.findFotoById(id);
    }

    public Long saveDano(Dano dano) {
        return danosRepository.save(dano).getId();
    }

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

    public List<DanosAmbientaisDTO> findAllDanosAmbientaisByOcorrencia(Integer idOcorrencia){
        return danosAmbientaisRepository.findAllDanosAmbientaisByOcorrencia(idOcorrencia);
    }

    public List<DanosHumanosDTO> findAllDanosHumanosByOcorrencia(Integer idOcorrencia){
        return danosHumanosRepository.findAllDanosHumanosByOcorrencia(idOcorrencia);
    }

    public List<DanosMateriaisDTO> findAllDanosMateriaisByOcorrencia(Integer idOcorrencia){
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

    public Integer deleteDanosMateriais(Integer danoId) {
        danosMateriaisRepository.deleteById(danoId);
        return danoId;
    }

    public Integer deleteDanosAmbientais(Integer danoId) {
        danosAmbientaisRepository.deleteById(danoId);
        return danoId;
    }

    public Integer deleteDanosHumanos(Integer danoId) {
        danosHumanosRepository.deleteById(danoId);
        return danoId;
    }
    //TODO byId, insert, update, delete

}
