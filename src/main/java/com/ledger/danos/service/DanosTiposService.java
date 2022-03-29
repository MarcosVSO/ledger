package com.ledger.danos.service;

import com.ledger.danos.entities.tipos.DanosAmbientaisTipo;
import com.ledger.danos.entities.tipos.DanosHumanosTipo;
import com.ledger.danos.entities.tipos.DanosMateriaisTipo;
import com.ledger.danos.repositories.tipos.DanosAmbientaisTipoRepository;
import com.ledger.danos.repositories.tipos.DanosHumanosTipoRepository;
import com.ledger.danos.repositories.tipos.DanosMateriaisTipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DanosTiposService {

    @Autowired
    private DanosAmbientaisTipoRepository danosAmbientaisTipoRepository;

    @Autowired
    private DanosMateriaisTipoRepository danosMateriaisTipoRepository;

    @Autowired
    private DanosHumanosTipoRepository danosHumanosTipoRepository;

    public Optional<DanosAmbientaisTipo> findDanoAmbientalTipoById(Integer id){
        return danosAmbientaisTipoRepository.findById(id);
    }

    public List<DanosAmbientaisTipo> findAllDanoAmbientalTipo(){
        return danosAmbientaisTipoRepository.findAll();
    }

    public Optional<DanosMateriaisTipo> findDanoMaterialTipoById(Integer id){
        return danosMateriaisTipoRepository.findById(id);
    }

    public List<DanosMateriaisTipo> findAllDanoMaterialTipo(){
        return danosMateriaisTipoRepository.findAll();
    }

    public Optional<DanosHumanosTipo> findDanoHumanoTipoById(Integer id){
        return danosHumanosTipoRepository.findById(id);
    }

}
