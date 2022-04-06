package com.ledger.ocorrencia.service;

import com.ledger.danos.dtos.DanosMateriaisSomaDTO;
import com.ledger.danos.entities.tipos.DanoTipo;
import com.ledger.danos.service.DanosService;
import com.ledger.danos.service.DanosTiposService;
import com.ledger.ocorrencia.dto.FideDTO;
import com.ledger.ocorrencia.entities.Ocorrencia;
import com.ledger.ocorrencia.repositories.OcorrenciaRepository;
import com.ledger.telefone.entities.Telefone;
import com.ledger.telefone.service.TelefoneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Autowired
    private TelefoneService telefoneService;

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

    public Optional<Ocorrencia> findById(Integer idOcorrencia){
        return ocorrenciaRepository.findById(idOcorrencia);
    }

    public FideDTO gerarFIDEOcorrencia(Integer idOcorrencia){
        Optional<Ocorrencia> ocorrencia = ocorrenciaRepository.findById(idOcorrencia);
        FideDTO fideDTO = modelMapper.map(ocorrencia.get(), FideDTO.class );

        Map<String, Integer> danosHumanosMapped = new HashMap<String, Integer>();
        for (DanoTipo dt : danosTiposService.findAllDanoTipoByCategoria("humano")){
            danosHumanosMapped.put(dt.getDescricao(), danosService.getSomaDanosHumanos(dt.getId(),idOcorrencia));
        }
        fideDTO.setDanosHumanosMapped(danosHumanosMapped);

        Map<String,Integer> danosAmbientaisMapped = new HashMap<String, Integer>();
        for (DanoTipo dt : danosTiposService.findAllDanoTipoByCategoria("ambiental")){
            danosAmbientaisMapped.put(dt.getDescricao(), danosService.getSomaDanosAmbientais(dt.getId(),idOcorrencia));
        }
        fideDTO.setDanosAmbientaisMapped(danosAmbientaisMapped);

        List<DanosMateriaisSomaDTO> danosMateriaisSomaDTOS = new ArrayList<DanosMateriaisSomaDTO>();
        for (DanoTipo dt : danosTiposService.findAllDanoTipoByCategoria("material")){
            DanosMateriaisSomaDTO danosMateriaisSomaDTO = danosService.getSomaDanosMateriais(dt.getId(), idOcorrencia, dt.getDescricao());
            danosMateriaisSomaDTOS.add(danosMateriaisSomaDTO);
        }
        fideDTO.setDanosMateriaisSoma(danosMateriaisSomaDTOS);

        return fideDTO;
    }

    public ResponseEntity<Slice<Ocorrencia>> paginateByCobradeAndStatus(Pageable page, String cobrade) {
        return new ResponseEntity<>(ocorrenciaRepository.findAllByCodCobrade(cobrade, page), HttpStatus.OK);
    }

    @Transactional
    public Integer salvarOcorrencia(Ocorrencia ocorrencia) {
        var telefones = ocorrencia.getInstInformanteTelefones();
        ocorrencia.setInstInformanteTelefones(null);
        Ocorrencia savedOcorrencia = ocorrenciaRepository.save(ocorrencia);
        salvarTelefonesOcorrencia(ocorrencia, telefones);
        return savedOcorrencia.getId();
    }

    private void salvarTelefonesOcorrencia(Ocorrencia ocorrencia, List<Telefone> telefones) {
        telefoneService.deleteByOcorrenciaId(ocorrencia.getId());
        telefones.forEach(tel -> {
            tel.setOcorrencia(ocorrencia);
            telefoneService.salvar(tel);
        });
    }
}
