package com.ledger.ocorrencia.rest;

import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.entities.DanosMateriais;
import com.ledger.danos.service.DanosService;
import com.ledger.ocorrencia.dto.FideDTO;
import com.ledger.ocorrencia.entities.Ocorrencia;
import com.ledger.ocorrencia.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/ocorrencias")
public class OcorrenciaRest {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @Autowired
    private DanosService danosService;

    @GetMapping
    public ResponseEntity<List<Ocorrencia>> findAll() {
        return ocorrenciaService.findAll();
    }

    @GetMapping("/cobrade/{cobrade}")
    public ResponseEntity<List<Ocorrencia>> findAllByCobrade(@PathVariable String cobrade) {
        return ocorrenciaService.findAllByCobrade(cobrade);
    }

    @GetMapping("/uf/{uf}")
    public ResponseEntity<List<Ocorrencia>> findAllByUf(@PathVariable String uf) {
        return ocorrenciaService.findAllByUf(uf);
    }

    @GetMapping("/municipio/{municipio}")
    public ResponseEntity<List<Ocorrencia>> findAllByMunicipio(@PathVariable String municipio) {
        return ocorrenciaService.findAllByMunicipio(municipio);
    }

    @GetMapping("/gerar-fide/{idOcorrencia}")
    public ResponseEntity<FideDTO> gerarFIDEOcorrencia(@PathVariable Integer idOcorrencia) {
        return ocorrenciaService.gerarFIDEOcorrencia(idOcorrencia);
    }

    @GetMapping("/{idOcorrencia}")
    public ResponseEntity<Ocorrencia> findById(@PathVariable Integer idOcorrencia) {
        return ocorrenciaService.findById(idOcorrencia);
    }

    @PostMapping("/add")
    public ResponseEntity<String> findAllByMunicipio(@RequestBody Ocorrencia ocorrencia) {
        return ocorrenciaService.save(ocorrencia);
    }

    @GetMapping("/{idOcorrencia}/danos-ambientais")
    public ResponseEntity<List<DanosAmbientais>> findAllDanosAmbientaisByOcorrencia(@PathVariable Integer idOcorrencia){
        return danosService.findAllDanosAmbientaisByOcorrencia(idOcorrencia);
    }

    @GetMapping("/{idOcorrencia}/danos-materiais")
    public ResponseEntity<List<DanosMateriais>> findAllDanosMateriaisByOcorrencia(@PathVariable Integer idOcorrencia){
        return danosService.findAllDanosMateriaisByOcorrencia(idOcorrencia);
    }

    @GetMapping("/{idOcorrencia}/danos-humanos")
    public ResponseEntity<List<DanosHumanos>> findAllDanosHumanosByOcorrencia(@PathVariable Integer idOcorrencia){
        return danosService.findAllDanosHumanosByOcorrencia(idOcorrencia);
    }

    //TODO  update, delete



}
