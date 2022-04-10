package com.ledger.danos.service;

import com.ledger.danos.entities.Tipo;
import com.ledger.danos.repositories.tipos.DanosTipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DanosTiposService {

    @Autowired
    private DanosTipoRepository danosTipoRepository;

    public Optional<Tipo> findDanoTipoById(Integer id){
        return danosTipoRepository.findById(id);
    }

    public List<Tipo> findAllDanoTipoByCategoria(String categoria){
        return danosTipoRepository.findAllByCategoria(categoria);
    }

    public List<Tipo> findAllTiposHumanos(){
        return danosTipoRepository.findAllByCategoria("humano");
    }

    public List<Tipo> findAllTiposAmbientais(){
        return danosTipoRepository.findAllByCategoria("ambiental");
    }

    public List<Tipo> findAllTiposMateriais(){
        return danosTipoRepository.findAllByCategoria("material");
    }

    public List<Tipo> findAllDanoTipo(){
        return danosTipoRepository.findAll();
    }

}
