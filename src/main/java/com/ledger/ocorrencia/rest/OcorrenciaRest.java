package com.ledger.ocorrencia.rest;

import com.ledger.danos.dtos.DanosAmbientaisDTO;
import com.ledger.danos.dtos.DanosMateriaisDTO;
import com.ledger.danos.service.DanosService;
import com.ledger.danos.dtos.DanosHumanosDTO;
import com.ledger.documento.UpdateDocumentService;
import com.ledger.ocorrencia.dto.FideDTO;
import com.ledger.ocorrencia.dto.OcorrenciaDTO;
import com.ledger.ocorrencia.dto.ListOcorrenciaDTO;
import com.ledger.ocorrencia.dto.mapper.OcorrenciaMapper;
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
@RequestMapping(value = "/ocorrencias")
public class OcorrenciaRest {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @Autowired
    private DanosService danosService;

    @Autowired
    private UpdateDocumentService updateDocumentService;

    @Autowired
    private OcorrenciaMapper ocorrenciaMapper;

    @GetMapping
    public ResponseEntity<List<Ocorrencia>> findAll() {
        return ocorrenciaService.findAll();
    }

    @GetMapping("/list")
    public ResponseEntity<Slice<ListOcorrenciaDTO>> listAllWithFilters(Pageable pageable, @RequestParam(required =
            false) String cobrade, @RequestParam(required = false) Integer municipioId) {
        var response = ocorrenciaService.paginateByCobradeAndStatus(pageable, cobrade, municipioId);
        return ResponseEntity.ok(response.map((ocorrenciaMapper::toListDTO)));
    }

    @GetMapping("/{idOcorrencia}")
    public ResponseEntity<OcorrenciaDTO> findByIdForDetails(@PathVariable Integer idOcorrencia) {
        var ocorrencia = ocorrenciaService.findById(idOcorrencia);
        if (ocorrencia.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ocorrenciaMapper.toDTO(ocorrencia.get()));
    }

    @PatchMapping()
    public ResponseEntity<Integer> updateOcorrencia(@RequestBody OcorrenciaDTO ocorrencia) {
        var ocorrenciaId = ocorrenciaService.salvarOcorrencia(ocorrenciaMapper.fromCreateToEntity(ocorrencia));
        return ResponseEntity.ok(ocorrenciaId);
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


    @PostMapping()
    public ResponseEntity<Integer> saveOcorrencia(@RequestBody OcorrenciaDTO ocorrencia) {
        var ocorrenciaId = ocorrenciaService.salvarOcorrencia(ocorrenciaMapper.fromCreateToEntity(ocorrencia));
        return ResponseEntity.ok(ocorrenciaId);
    }

    @GetMapping("/{idOcorrencia}/danos-ambientais")
    public ResponseEntity<List<DanosAmbientaisDTO>> findAllDanosAmbientaisByOcorrencia(@PathVariable Integer idOcorrencia) {
        var response = danosService.findAllDanosAmbientaisByOcorrencia(idOcorrencia);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idOcorrencia}/danos-materiais")
    public ResponseEntity<List<DanosMateriaisDTO>> findAllDanosMateriaisByOcorrencia(@PathVariable Integer idOcorrencia) {
        var response = danosService.findAllDanosMateriaisByOcorrencia(idOcorrencia);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idOcorrencia}/danos-humanos")
    public ResponseEntity<List<DanosHumanosDTO>> findAllDanosHumanosByOcorrencia(@PathVariable Integer idOcorrencia) {
        var response = danosService.findAllDanosHumanosByOcorrencia(idOcorrencia);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/gerar-documento/{idOcorrencia}", method = RequestMethod.GET, produces =
            "application" + "/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public @ResponseBody
    byte[] getDoc(@PathVariable Integer idOcorrencia) {
        FideDTO fideDto = ocorrenciaService.gerarFIDEOcorrencia(idOcorrencia);
        return updateDocumentService.updateDocument(fideDto);
    }

    //TODO  update, delete


}
