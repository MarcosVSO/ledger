package com.ledger.ocorrencia.service;

import com.ledger.danos.dtos.DanosMateriaisSomaDTO;
import com.ledger.danos.entities.Tipo;
import com.ledger.danos.service.DanosService;
import com.ledger.danos.service.DanosTiposService;
import com.ledger.localidades.service.LocalidadeService;
import com.ledger.ocorrencia.dto.FideDTO;
import com.ledger.ocorrencia.dto.mapper.OcorrenciaMapper;
import com.ledger.ocorrencia.entities.Ocorrencia;
import com.ledger.ocorrencia.repositories.AreaAfetadaRepository;
import com.ledger.ocorrencia.repositories.InstituicaoInformanteRepository;
import com.ledger.ocorrencia.repositories.OcorrenciaRepository;
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
    private final OcorrenciaRepository ocorrenciaRepository;
    private final AreaAfetadaRepository areaAfetadaRepository;
    private final InstituicaoInformanteRepository instituicaoInformanteRepository;
    private final DanosService danosService;
    private final DanosTiposService danosTiposService;
    private final ModelMapper modelMapper;
    private final LocalidadeService localidadeService;

    @Autowired
    private OcorrenciaMapper ocorrenciaMapper;

    @Autowired
    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository, AreaAfetadaRepository areaAfetadaRepository,
                             InstituicaoInformanteRepository instituicaoInformanteRepository,
                             DanosService danosService, DanosTiposService danosTiposService, ModelMapper modelMapper,
                             LocalidadeService localidadeService) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.areaAfetadaRepository = areaAfetadaRepository;
        this.danosService = danosService;
        this.danosTiposService = danosTiposService;
        this.modelMapper = modelMapper;
        this.localidadeService = localidadeService;
        this.instituicaoInformanteRepository = instituicaoInformanteRepository;
    }

    public ResponseEntity<List<Ocorrencia>> findAll() {
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAll();
        return new ResponseEntity<List<Ocorrencia>>(ocorrencias, HttpStatus.OK);
    }

    public ResponseEntity<List<Ocorrencia>> findAllByCobrade(String cobrade) {
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAllByCobrade(cobrade);
        return new ResponseEntity<List<Ocorrencia>>(ocorrencias, HttpStatus.OK);
    }

    public ResponseEntity<List<Ocorrencia>> findAllByUf(String uf) {
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAllBySiglaUf(uf);
        return new ResponseEntity<List<Ocorrencia>>(ocorrencias, HttpStatus.OK);
    }

    public ResponseEntity<List<Ocorrencia>> findAllByMunicipio(String municipio) {
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAllByMunicipio(municipio);
        return new ResponseEntity<List<Ocorrencia>>(ocorrencias, HttpStatus.OK);
    }

    public ResponseEntity<String> save(Ocorrencia ocorrencia) {
        ocorrenciaRepository.save(ocorrencia);
        return new ResponseEntity<String>("Ocorrência registrada com sucesso", HttpStatus.OK);
    }

    public Optional<Ocorrencia> findById(Integer idOcorrencia) {
        return ocorrenciaRepository.findById(idOcorrencia);
    }

    public FideDTO gerarFIDEOcorrencia(Integer idOcorrencia) {
        Optional<Ocorrencia> ocorrencia = ocorrenciaRepository.findById(idOcorrencia);
        FideDTO fideDTO = new FideDTO();
        fideDTO.setDadosOcorrencia(ocorrenciaMapper.toDTO(ocorrencia.get()));

        Map<String, Integer> danosAmbientaisMapped = new HashMap<String, Integer>();
        for (Tipo dt : danosTiposService.findAllDanoTipoByCategoria("ambiental")) {
            danosAmbientaisMapped.put(dt.getDescricao(), danosService.getSomaDanosAmbientais(dt.getId(), idOcorrencia));
        }
        fideDTO.setDanosAmbientaisMapped(danosAmbientaisMapped);

        List<DanosMateriaisSomaDTO> danosMateriaisSomaDTOS = new ArrayList<DanosMateriaisSomaDTO>();
        for (Tipo dt : danosTiposService.findAllDanoTipoByCategoria("material")) {
            DanosMateriaisSomaDTO danosMateriaisSomaDTO = danosService.getSomaDanosMateriais(dt.getId(), idOcorrencia
                    , dt.getDescricao());
            danosMateriaisSomaDTOS.add(danosMateriaisSomaDTO);
        }
        fideDTO.setDanosMateriaisSoma(danosMateriaisSomaDTOS);


        /*Map<String, Integer> danosHumanosMapped = new HashMap<String, Integer>();
        for (Tipo dt : danosTiposService.findAllDanoTipoByCategoria("humano")) {
            danosHumanosMapped.put(dt.getDescricao(), danosService.getSomaDanosHumanos(dt.getId(), idOcorrencia));
        }
        fideDTO.setDanosHumanosMapped(danosHumanosMapped);



        */

        return fideDTO;
    }

    public Slice<Ocorrencia> paginateByCobradeAndStatus(Pageable page, String cobrade, Integer municipio) {
        return ocorrenciaRepository.findAllByCodCobradeAndLocalidade(cobrade, municipio, page);
    }

    @Transactional
    public Integer salvarOcorrencia(Ocorrencia ocorrencia) {
        Ocorrencia savedOcorrencia = ocorrenciaRepository.save(ocorrencia);

        var areaAfetada = ocorrencia.getAreaAfetada();
        areaAfetada.setOcorrencia(savedOcorrencia);
        areaAfetadaRepository.save(areaAfetada);

        var informante = ocorrencia.getInstituicaoInformante();
        informante.setOcorrencia(savedOcorrencia);
        instituicaoInformanteRepository.save(informante);

        return savedOcorrencia.getId();
    }
}
