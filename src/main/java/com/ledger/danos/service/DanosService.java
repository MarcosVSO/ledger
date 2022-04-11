package com.ledger.danos.service;

import com.ledger.danos.dtos.DanosAmbientaisDTO;
import com.ledger.danos.dtos.DanosHumanosDTO;
import com.ledger.danos.dtos.DanosMateriaisDTO;
import com.ledger.danos.dtos.DanosMateriaisSomaDTO;
import com.ledger.danos.entities.*;
import com.ledger.danos.repositories.*;
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
    private FotosRepository fotosRepository;

    public DanosService(DanosMateriaisRepository danosMateriaisRepository,
                        DanosAmbientaisRepository danosAmbientaisRepository,
                        DanosHumanosRepository danosHumanosRepository, DanosRepository danosRepository,
                        FotosRepository fotosRepository) {
        this.danosMateriaisRepository = danosMateriaisRepository;
        this.danosAmbientaisRepository = danosAmbientaisRepository;
        this.danosHumanosRepository = danosHumanosRepository;
        this.danosRepository = danosRepository;
        this.fotosRepository = fotosRepository;
    }

    public List<Dano> findDanosByOcorrenciaId(Integer ocorrenciaId) {
        return danosRepository.findAllByOcorrencia_Id(ocorrenciaId);
    }

    public Optional<Dano> findDanoById(Long id) {
        return danosRepository.findById(id);
    }

    public Optional<Foto> findFotoById(Long id) {
        return fotosRepository.findFotoById(id);
    }

    public Long saveDano(Dano dano) {
        return danosRepository.save(dano).getId();
    }

    public List<DanosAmbientais> findDanosAmbientaisByDanoId(Long id) {
        return danosAmbientaisRepository.findAllDanosAmbientaisByDanoId(id);
    }

    public List<DanosHumanos> findDanosHumanosByDanoId(Long id) {
        return danosHumanosRepository.findAllDanosHumanosByDanoId(id);
    }

    public List<DanosMateriais> findDanosMateriaisByDanoId(Long id) {
        return danosMateriaisRepository.findAllDanosMateriaisByDanoId(id);
    }

    public Integer getSomaDanosHumanos(Integer danoTipo, Integer idOcorrencia) {
        return danosHumanosRepository.getSomaDanosHumanos(danoTipo, idOcorrencia);
    }

    public Integer getSomaDanosAmbientais(Integer danoTipo, Integer idOcorrencia) {
        return danosAmbientaisRepository.getSomaDanosAmbientais(danoTipo, idOcorrencia);
    }

    public DanosMateriaisSomaDTO getSomaDanosMateriais(Integer danoTipo, Integer idOcorrencia, String tipoDano) {
        return danosMateriaisRepository.getSomaDanosMateriais(danoTipo, idOcorrencia, tipoDano);
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
}
