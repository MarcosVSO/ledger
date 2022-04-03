package com.ledger.ocorrencia.rest;

import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.entities.DanosMateriais;
import com.ledger.danos.service.DanosService;
import com.ledger.documento.UpdateDocumentService;
import com.ledger.ocorrencia.dto.FideDTO;
import com.ledger.ocorrencia.entities.Ocorrencia;
import com.ledger.ocorrencia.service.OcorrenciaService;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@RestController
@RequestMapping(value="/ocorrencias")
public class OcorrenciaRest {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @Autowired
    private DanosService danosService;

    @Autowired
    private UpdateDocumentService updateDocumentService;

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
        return new ResponseEntity<FideDTO>(ocorrenciaService.gerarFIDEOcorrencia(idOcorrencia), HttpStatus.OK);
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

    /*@GetMapping("/gerar-documento/{idOcorrencia}")
    public ResponseEntity gerarDocumentoFIDEOcorrencia(@PathVariable Integer idOcorrencia){
        FideDTO fideDto = ocorrenciaService.gerarFIDEOcorrencia(idOcorrencia);
        updateDocumentService.updateDocument(fideDto);
        return new ResponseEntity(HttpStatus.OK);

    }*/

    @RequestMapping(value = "/gerar-documento/{idOcorrencia}",method = RequestMethod.GET, produces="application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public @ResponseBody byte[] getDoc(@PathVariable Integer idOcorrencia) {
        FideDTO fideDto = ocorrenciaService.gerarFIDEOcorrencia(idOcorrencia);
        return updateDocumentService.updateDocument(fideDto);
    }

    //TODO  update, delete



}
