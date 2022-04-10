package com.ledger.ocorrencia.rest;

import com.ledger.danos.dtos.DanoCreateDTO;
import com.ledger.danos.dtos.mappers.DanoMapper;
import com.ledger.documento.UpdateDocumentService;
import com.ledger.ocorrencia.dto.FideDTO;
import com.ledger.ocorrencia.dto.OcorrenciaDTO;
import com.ledger.ocorrencia.dto.ListOcorrenciaDTO;
import com.ledger.ocorrencia.dto.mapper.OcorrenciaMapper;
import com.ledger.ocorrencia.entities.Ocorrencia;
import com.ledger.ocorrencia.service.OcorrenciaService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/ocorrencias")
public class OcorrenciaRest {
    private OcorrenciaService ocorrenciaService;
    private UpdateDocumentService updateDocumentService;
    private OcorrenciaMapper ocorrenciaMapper;
    private DanoMapper danoMapper;

    public OcorrenciaRest(OcorrenciaService ocorrenciaService, UpdateDocumentService updateDocumentService,
                          OcorrenciaMapper ocorrenciaMapper, DanoMapper danoMapper) {
        this.ocorrenciaService = ocorrenciaService;
        this.updateDocumentService = updateDocumentService;
        this.ocorrenciaMapper = ocorrenciaMapper;
        this.danoMapper = danoMapper;
    }

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

    @PostMapping()
    public ResponseEntity<Integer> saveOcorrencia(@RequestBody OcorrenciaDTO ocorrencia) {
        var ocorrenciaId = ocorrenciaService.salvarOcorrencia(ocorrenciaMapper.toEntity(ocorrencia));
        return ResponseEntity.ok(ocorrenciaId);
    }

    @PatchMapping()
    public ResponseEntity<Integer> updateOcorrencia(@RequestBody OcorrenciaDTO ocorrencia) {
        var ocorrenciaId = ocorrenciaService.atualizarOcorrencia(ocorrenciaMapper.toEntity(ocorrencia));
        return ResponseEntity.ok(ocorrenciaId);
    }

    @GetMapping("/{idOcorrencia}/danos")
    public ResponseEntity<List<DanoCreateDTO>> findAllDanosByOcorrencia(@PathVariable Integer idOcorrencia) {
        var response = ocorrenciaService.findDanos(idOcorrencia);
        return ResponseEntity.ok(response.stream().map(danoMapper::toSimpleDTO).collect(Collectors.toList()));
    }

    @PostMapping(value = "/{idOcorrencia}/danos")
    public ResponseEntity<Long> salvarDanoByOcorrencia(DanoCreateDTO danoCreateDTO, @PathVariable Integer idOcorrencia) {
        var danoId = ocorrenciaService.salvarDano(idOcorrencia, danoMapper.toEntity(danoCreateDTO));
        return ResponseEntity.ok(danoId);
    }

    @GetMapping("/gerar-fide/{idOcorrencia}")
    public ResponseEntity<FideDTO> gerarFIDEOcorrencia(@PathVariable Integer idOcorrencia) {
        return new ResponseEntity<FideDTO>(ocorrenciaService.gerarFIDEOcorrencia(idOcorrencia), HttpStatus.OK);
    }

    @RequestMapping(value = "/gerar-documento/{idOcorrencia}", method = RequestMethod.GET, produces =
            "application" + "/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public @ResponseBody
    byte[] getDoc(@PathVariable Integer idOcorrencia) {
        FideDTO fideDto = ocorrenciaService.gerarFIDEOcorrencia(idOcorrencia);
        return updateDocumentService.updateDocument(fideDto);
    }
}
