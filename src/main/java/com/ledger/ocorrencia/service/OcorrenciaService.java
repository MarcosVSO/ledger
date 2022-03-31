package com.ledger.ocorrencia.service;

import com.ledger.danos.dtos.DanosMateriaisSomaDTO;
import com.ledger.danos.entities.tipos.DanosAmbientaisTipo;
import com.ledger.danos.entities.tipos.DanosHumanosTipo;
import com.ledger.danos.entities.tipos.DanosMateriaisTipo;
import com.ledger.danos.service.DanosService;
import com.ledger.danos.service.DanosTiposService;
import com.ledger.ocorrencia.dto.FideDTO;
import com.ledger.ocorrencia.entities.Ocorrencia;
import com.ledger.ocorrencia.repositories.OcorrenciaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OcorrenciaService {
    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private DanosService danosService;

    @Autowired
    private DanosTiposService danosTiposService;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<List<Ocorrencia>> findAll(){
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAll();
        return new ResponseEntity<List<Ocorrencia>>(ocorrencias, HttpStatus.OK);
    }

    public ResponseEntity<List<Ocorrencia>> findAllByCobrade(String cobrade){
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAllByCobrade(cobrade);
        return new ResponseEntity<List<Ocorrencia>>(ocorrencias, HttpStatus.OK);
    }

    public ResponseEntity<List<Ocorrencia>> findAllByUf(String uf){
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAllByUf(uf);
        return new ResponseEntity<List<Ocorrencia>>(ocorrencias, HttpStatus.OK);
    }

    public ResponseEntity<List<Ocorrencia>> findAllByMunicipio(String municipio){
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAllByMunicipio(municipio);
        return new ResponseEntity<List<Ocorrencia>>(ocorrencias, HttpStatus.OK);
    }

    public ResponseEntity<String> save(Ocorrencia ocorrencia){
        ocorrenciaRepository.save(ocorrencia);
        return new ResponseEntity<String>("Ocorrência registrada com sucesso",HttpStatus.OK);
    }

    public ResponseEntity<Ocorrencia> findById(Integer idOcorrencia){
        Optional<Ocorrencia> ocorrencia = ocorrenciaRepository.findById(idOcorrencia);
        return new ResponseEntity<Ocorrencia>(ocorrencia.get(), HttpStatus.OK);
    }

    public ResponseEntity<FideDTO> gerarFIDEOcorrencia(Integer idOcorrencia){
        Optional<Ocorrencia> ocorrencia = ocorrenciaRepository.findById(idOcorrencia);
        FideDTO fideDTO = modelMapper.map(ocorrencia.get(), FideDTO.class );

        Map<String, Integer> danosHumanosMapped = new HashMap<String, Integer>();
        for (DanosHumanosTipo dht : danosTiposService.findAllDanoHumanoTipo()){
            danosHumanosMapped.put(dht.getDescricao(), danosService.getSomaDanosHumanos(dht.getId(),idOcorrencia));
        }
        fideDTO.setDanosHumanosMapped(danosHumanosMapped);

        Map<String,Integer> danosAmbientaisMapped = new HashMap<String, Integer>();
        for (DanosAmbientaisTipo dat : danosTiposService.findAllDanoAmbientalTipo()){
            danosAmbientaisMapped.put(dat.getDescricao(), danosService.getSomaDanosAmbientais(dat.getId(),idOcorrencia));
        }
        fideDTO.setDanosAmbientaisMapped(danosAmbientaisMapped);

        List<DanosMateriaisSomaDTO> danosMateriaisSomaDTOS = new ArrayList<DanosMateriaisSomaDTO>();
        for (DanosMateriaisTipo dmt : danosTiposService.findAllDanoMaterialTipo()){
            DanosMateriaisSomaDTO danosMateriaisSomaDTO = danosService.getSomaDanosMateriais(dmt.getId(), idOcorrencia, dmt.getDescricao());
            danosMateriaisSomaDTOS.add(danosMateriaisSomaDTO);
        }
        fideDTO.setDanosMateriaisSoma(danosMateriaisSomaDTOS);

        return new ResponseEntity<FideDTO>(fideDTO, HttpStatus.OK);
    }

    public ResponseEntity<Slice<Ocorrencia>> paginateByCobradeAndStatus(Pageable page, String cobrade) {
        return new ResponseEntity<>(ocorrenciaRepository.findAllByCodCobrade(cobrade, page), HttpStatus.OK);
    }

}
