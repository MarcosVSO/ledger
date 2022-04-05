package com.ledger.ocorrencia.rest;

import com.ledger.danos.service.DanosService;
import com.ledger.danos.dtos.DanosAmbientaisListDTO;
import com.ledger.danos.dtos.DanosHumanosListDTO;
import com.ledger.danos.dtos.DanosMateriaisListDTO;
import com.ledger.documento.UpdateDocumentService;
import com.ledger.ocorrencia.dto.FideDTO;
import com.ledger.ocorrencia.entities.Ocorrencia;
import com.ledger.ocorrencia.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private UpdateDocumentService updateDocumentService;

    @GetMapping
    public ResponseEntity<List<Ocorrencia>> findAll() {
        return ocorrenciaService.findAll();
    }

    @GetMapping("/list")
    public ResponseEntity<Slice<Ocorrencia>> listAllWithFilters(
            Pageable pageable,
            @RequestParam(required = false) String cobrade) {
        return ocorrenciaService.paginateByCobradeAndStatus(pageable, cobrade);
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
    public ResponseEntity<List<DanosAmbientaisListDTO>> findAllDanosAmbientaisByOcorrencia(@PathVariable Integer idOcorrencia) {
        var response = danosService.findAllDanosAmbientaisByOcorrencia(idOcorrencia);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idOcorrencia}/danos-materiais")
    public ResponseEntity<List<DanosMateriaisListDTO>> findAllDanosMateriaisByOcorrencia(@PathVariable Integer idOcorrencia) {
        var response = danosService.findAllDanosMateriaisByOcorrencia(idOcorrencia);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idOcorrencia}/danos-humanos")
    public ResponseEntity<List<DanosHumanosListDTO>> findAllDanosHumanosByOcorrencia(@PathVariable Integer idOcorrencia) {
        var response = danosService.findAllDanosHumanosByOcorrencia(idOcorrencia);
        return ResponseEntity.ok(response);
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
