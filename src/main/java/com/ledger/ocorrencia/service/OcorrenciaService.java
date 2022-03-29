package com.ledger.ocorrencia.service;

import com.ledger.danos.dtos.DanosHumanosSomaDTO;
import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.service.DanosService;
import com.ledger.ocorrencia.dto.FideDTO;
import com.ledger.ocorrencia.entities.Ocorrencia;
import com.ledger.ocorrencia.repositories.OcorrenciaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OcorrenciaService {
    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private DanosService danosService;

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

        /*List<DanosHumanosSomaDTO> danosHumanosSomaDTOList = danosService.getSomaDanosHumanos(idOcorrencia);
        Map<Integer, Integer> danosAmbientaisTemp = new HashMap<Integer, Integer>();
        for (DanosAmbientais d : ocorrencia.get().getDanosAmbientais()){
            danosAmbientaisTemp.put(d.getDanoAmbientalTipo(),d.getPopulacaoAtingida());
        }
        fideDTO.setDanosAmbientaisMapped(danosAmbientaisTemp);

        Map<String, Integer> danosHumanosTemp = new HashMap<String, Integer>();
        for (DanosHumanos d : ocorrencia.get().getDanosHumanos()){
            danosHumanosTemp.put(d.getDanoHumanoTipo(),d.getNumeroPessoas());
        }
        fideDTO.setDanosHumanosMapped(danosHumanosTemp);*/
        return new ResponseEntity<FideDTO>(fideDTO, HttpStatus.OK);
    }

}
