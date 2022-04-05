package com.ledger.danos.service;

import com.ledger.danos.entities.tipos.DanoTipo;
import com.ledger.danos.repositories.tipos.DanosTipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DanosTiposService {

    @Autowired
    private DanosTipoRepository danosTipoRepository;

    public Optional<DanoTipo> findDanoTipoById(Integer id){
        return danosTipoRepository.findById(id);
    }

    public List<DanoTipo> findAllDanoTipoByCategoria(String categoria){
        return danosTipoRepository.findAllByCategoria(categoria);
    }

    public List<DanoTipo> findAllTiposHumanos(){
        return danosTipoRepository.findAllByCategoria("humano");
    }

    public List<DanoTipo> findAllTiposAmbientais(){
        return danosTipoRepository.findAllByCategoria("ambiental");
    }

    public List<DanoTipo> findAllTiposMateriais(){
        return danosTipoRepository.findAllByCategoria("material");
    }

    public List<DanoTipo> findAllDanoTipo(){
        return danosTipoRepository.findAll();
    }

}
