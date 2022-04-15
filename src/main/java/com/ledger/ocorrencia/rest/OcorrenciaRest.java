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
import com.ledger.rest.dto.IdResponseDTO;
import com.ledger.rest.dto.mappers.IdResponseMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/ocorrencias")
public class OcorrenciaRest {
    private final OcorrenciaService ocorrenciaService;
    private final UpdateDocumentService updateDocumentService;
    private final OcorrenciaMapper ocorrenciaMapper;
    private final DanoMapper danoMapper;
    private final IdResponseMapper idResponseMapper;

    public OcorrenciaRest(OcorrenciaService ocorrenciaService, UpdateDocumentService updateDocumentService,
                          OcorrenciaMapper ocorrenciaMapper, DanoMapper danoMapper, IdResponseMapper idResponseMapper) {
        this.ocorrenciaService = ocorrenciaService;
        this.updateDocumentService = updateDocumentService;
        this.ocorrenciaMapper = ocorrenciaMapper;
        this.danoMapper = danoMapper;
        this.idResponseMapper = idResponseMapper;
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

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{idOcorrencia}")
    public void deleteById(@PathVariable Integer idOcorrencia) {
        ocorrenciaService.deleteById(idOcorrencia);
    }

    @PostMapping()
    public ResponseEntity<IdResponseDTO> saveOcorrencia(@RequestBody OcorrenciaDTO ocorrencia) {
        var ocorrenciaId = ocorrenciaService.salvarOcorrencia(ocorrenciaMapper.toEntity(ocorrencia));
        return ResponseEntity.ok(idResponseMapper.toDto(ocorrenciaId));
    }

    @PatchMapping()
    public ResponseEntity<IdResponseDTO> updateOcorrencia(@RequestBody OcorrenciaDTO ocorrencia) {
        var ocorrenciaId = ocorrenciaService.atualizarOcorrencia(ocorrenciaMapper.toEntity(ocorrencia));
        return ResponseEntity.ok(idResponseMapper.toDto(ocorrenciaId));
    }

    @GetMapping("/{idOcorrencia}/danos")
    public ResponseEntity<List<DanoCreateDTO>> findAllDanosByOcorrencia(@PathVariable Integer idOcorrencia) {
        var response = ocorrenciaService.findDanos(idOcorrencia);
        return ResponseEntity.ok(response.stream().map(danoMapper::toSimpleDTO).collect(Collectors.toList()));
    }

    @PostMapping(value = "/{idOcorrencia}/danos")
    public ResponseEntity<IdResponseDTO> salvarDanoByOcorrencia(DanoCreateDTO danoCreateDTO,
                                                                @PathVariable Integer idOcorrencia) {
        var danoId = ocorrenciaService.salvarDano(idOcorrencia, danoMapper.toEntity(danoCreateDTO));
        return ResponseEntity.ok(idResponseMapper.toDto(danoId));
    }

    @GetMapping("/gerar-fide/{idOcorrencia}")
    public ResponseEntity<FideDTO> gerarFIDEOcorrencia(@PathVariable Integer idOcorrencia) {
        return new ResponseEntity<FideDTO>(ocorrenciaService.gerarFIDEOcorrencia(idOcorrencia), HttpStatus.OK);
    }

    @RequestMapping(value = "/gerar-documento/{idOcorrencia}", method = RequestMethod.GET, produces =
            "application" + "/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public ResponseEntity<byte[]> getDoc(@PathVariable Integer idOcorrencia) {
        FideDTO fideDto = ocorrenciaService.gerarFIDEOcorrencia(idOcorrencia);
        String fileName =
                "ocorrencia-" + fideDto.getDadosOcorrencia().getUf().getNome() + "-" + fideDto.getDadosOcorrencia().getData().toString() + ".docx";
        var docBytes = updateDocumentService.updateDocument(fideDto);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + fileName + "\"").body(docBytes);
    }
}
