package com.ledger.localidades.service;

import com.ledger.localidades.entities.Municipio;
import com.ledger.localidades.entities.Uf;
import com.ledger.localidades.repository.MunicipioRepository;
import com.ledger.localidades.repository.UfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalidadeService {
    private final MunicipioRepository municipioRepository;
    private final UfRepository ufRepository;

    @Autowired
    public LocalidadeService(MunicipioRepository municipioRepository, UfRepository ufRepository) {
        this.municipioRepository = municipioRepository;
        this.ufRepository = ufRepository;
    }

    public List<Uf> findAllEstados() {
        return ufRepository.findAll();
    }

    public Optional<Uf> findEstadoById(Integer id) {
        return ufRepository.findById(id);
    }

    public List<Municipio> findMunicipiosByUF(Integer uf){
        return municipioRepository.findAllByUfId(uf);
    }

    public Optional<Municipio> findMunicipioById(Integer id){
        return municipioRepository.findById(id);
    }
}
