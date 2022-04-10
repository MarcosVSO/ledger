package com.ledger.ocorrencia.service;

import com.ledger.danos.dtos.DanosMateriaisSomaDTO;
import com.ledger.danos.entities.Dano;
import com.ledger.danos.entities.Foto;
import com.ledger.danos.entities.Tipo;
import com.ledger.danos.service.DanosService;
import com.ledger.danos.service.DanosTiposService;
import com.ledger.localidades.service.LocalidadeService;
import com.ledger.ocorrencia.dto.FideDTO;
import com.ledger.ocorrencia.entities.AreaAfetada;
import com.ledger.ocorrencia.entities.InstituicaoInformante;
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
        return new ResponseEntity<>(ocorrencias, HttpStatus.OK);
    }

    public Optional<Ocorrencia> findById(Integer idOcorrencia) {
        return ocorrenciaRepository.findById(idOcorrencia);
    }

    public FideDTO gerarFIDEOcorrencia(Integer idOcorrencia) {
        Optional<Ocorrencia> ocorrencia = ocorrenciaRepository.findById(idOcorrencia);
        FideDTO fideDTO = modelMapper.map(ocorrencia.get(), FideDTO.class);

        Map<String, Integer> danosHumanosMapped = new HashMap<String, Integer>();
        for (Tipo dt : danosTiposService.findAllDanoTipoByCategoria("humano")) {
            danosHumanosMapped.put(dt.getDescricao(), danosService.getSomaDanosHumanos(dt.getId(), idOcorrencia));
        }
        fideDTO.setDanosHumanosMapped(danosHumanosMapped);

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

        return fideDTO;
    }

    public Slice<Ocorrencia> paginateByCobradeAndStatus(Pageable page, String cobrade, Integer municipio) {
        return ocorrenciaRepository.findAllByCodCobradeAndLocalidade(cobrade, municipio, page);
    }

    public List<Dano> findDanos(Integer idOcorrencia) {
        return danosService.findDanosByOcorrenciaId(idOcorrencia);
    }

    @Transactional
    public Long salvarDano(Integer idOcorrencia, Dano dano) {
        var ocorrencia = ocorrenciaRepository.findById(idOcorrencia);
        if (ocorrencia.isEmpty()) {
            throw new RuntimeException();
        }
        dano.setOcorrencia(ocorrencia.get());

        for (Foto foto : dano.getFotos()) {
            foto.setDano(dano);
        }
        return danosService.saveDano(dano);
    }

    @Transactional
    public Integer salvarOcorrencia(Ocorrencia ocorrencia) {
        var areaAfetada = ocorrencia.getAreaAfetada();
        var informante = ocorrencia.getInstituicaoInformante();
        areaAfetada.setOcorrencia(ocorrencia);
        informante.setOcorrencia(ocorrencia);

        ocorrenciaRepository.save(ocorrencia);

        return ocorrencia.getId();
    }

    @Transactional
    public Integer atualizarOcorrencia(Ocorrencia ocorrencia) {
        if (ocorrencia.getId() == null) {
            throw new RuntimeException();
        }

        var ocorrenciaOptional = ocorrenciaRepository.findById(ocorrencia.getId());

        if (ocorrenciaOptional.isEmpty()) {
            throw new RuntimeException();
        }

        var ocorrenciaDb = ocorrenciaOptional.get();
        ocorrenciaDb.setCoordenadas(ocorrencia.getCoordenadas());
        ocorrenciaDb.setDcInformada(ocorrencia.getDcInformada());
        ocorrenciaDb.setSedecInformado(ocorrencia.getSedecInformado());
        ocorrenciaDb.setMunicipio(ocorrencia.getMunicipio());

        ocorrenciaDb.setAreaAfetada(atualizarAreaAfetada(ocorrencia, ocorrencia.getAreaAfetada()));
        ocorrenciaDb.setInstituicaoInformante(atualizarInformante(ocorrencia, ocorrencia.getInstituicaoInformante()));

        ocorrenciaRepository.save(ocorrenciaDb);

        return ocorrenciaDb.getId();
    }

    private AreaAfetada atualizarAreaAfetada(Ocorrencia ocorrencia, AreaAfetada areaAfetada) {
        var areaDb = areaAfetadaRepository.findByOcorrencia_Id(ocorrencia.getId()).get();
        areaDb.setResidencial(areaAfetada.getResidencial());
        areaDb.setComercial(areaAfetada.getComercial());
        areaDb.setIndustrial(areaAfetada.getIndustrial());
        areaDb.setAgricola(areaAfetada.getAgricola());
        areaDb.setPecuaria(areaAfetada.getPecuaria());
        areaDb.setExtrativismoVegetal(areaAfetada.getExtrativismoVegetal());
        areaDb.setReservaFlorestal(areaAfetada.getReservaFlorestal());
        areaDb.setMineracao(areaAfetada.getMineracao());
        areaDb.setTurismoOutras(areaAfetada.getTurismoOutras());
        return areaDb;
    }

    private InstituicaoInformante atualizarInformante(Ocorrencia ocorrencia,
                                                      InstituicaoInformante instituicaoInformante) {
        var informanteDb = instituicaoInformanteRepository.findByOcorrencia_Id(ocorrencia.getId()).get();
        informanteDb.setNome(instituicaoInformante.getNome());
        informanteDb.setResponsavel(instituicaoInformante.getResponsavel());
        informanteDb.setTelefones(instituicaoInformante.getTelefones());
        return informanteDb;
    }
}
