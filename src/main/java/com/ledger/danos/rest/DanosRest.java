package com.ledger.danos.rest;

import com.ledger.danos.dtos.DanoDetailsDTO;
import com.ledger.danos.dtos.DanosAmbientaisDTO;
import com.ledger.danos.dtos.DanosHumanosDTO;
import com.ledger.danos.dtos.DanosMateriaisDTO;
import com.ledger.danos.dtos.mappers.DanoMapper;
import com.ledger.rest.dto.IdResponseDTO;
import com.ledger.danos.dtos.create.DanosAmbientaisCreateDTO;
import com.ledger.danos.dtos.create.DanosHumanosCreateDTO;
import com.ledger.danos.dtos.create.DanosMateriaisCreateDTO;
import com.ledger.danos.dtos.mappers.DanosAmbientaisMapper;
import com.ledger.danos.dtos.mappers.DanosHumanosMapper;
import com.ledger.danos.dtos.mappers.DanosMateriaisMapper;
import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.entities.DanosMateriais;
import com.ledger.danos.service.DanosService;
import com.ledger.rest.dto.mappers.IdResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/danos")
@Validated
public class DanosRest {

    private final DanosService danosService;
    private final DanosAmbientaisMapper danosAmbientaisMapper;
    private final DanosHumanosMapper danosHumanosMapper;
    private final DanosMateriaisMapper danosMateriaisMapper;
    private final DanoMapper danoMapper;
    private IdResponseMapper idResponseMapper;

    @Autowired
    public DanosRest(DanosService danosService, DanosAmbientaisMapper danosAmbientaisMapper,
                     DanosHumanosMapper danosHumanosMapper, DanosMateriaisMapper danosMateriaisMapper,
                     DanoMapper danoMapper, IdResponseMapper idResponseMapper) {
        this.danosService = danosService;
        this.danosAmbientaisMapper = danosAmbientaisMapper;
        this.danosHumanosMapper = danosHumanosMapper;
        this.danosMateriaisMapper = danosMateriaisMapper;
        this.danoMapper = danoMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DanoDetailsDTO> findDanoById(@PathVariable("id") Long id) {
        var response = danosService.findDanoById(id);
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(danoMapper.toDTOWithImages(response.get()));
    }

    @GetMapping("/{id}/fotos/{fotoId}")
    public ResponseEntity<byte[]> findDanoFotoById(@PathVariable("fotoId") Long fotoId) {
        var response = danosService.findFotoById(fotoId);
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var file = response.get();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + file.getName() + "\"")
                .contentType(MediaType.valueOf(file.getContentType()))
                .body(response.get().getData());
    }

    @GetMapping("/{id}/ambientais")
    public ResponseEntity<List<DanosAmbientaisDTO>> findDanosAmbientaisByDanoId(@PathVariable("id") Long id) {
        var response = danosService.findDanosAmbientaisByDanoId(id);
        return ResponseEntity.ok(response.stream().map(danosAmbientaisMapper::toListDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}/humanos")
    public ResponseEntity<List<DanosHumanosDTO>> findDanosHumanosByDanoId(@PathVariable("id") Long id) {
        var response = danosService.findDanosHumanosByDanoId(id);
        return ResponseEntity.ok(response.stream().map(danosHumanosMapper::toListDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}/materiais")
    public ResponseEntity<List<DanosMateriaisDTO>> findDanosMateriaisByDanoId(@PathVariable("id") Long id) {
        var response = danosService.findDanosMateriaisByDanoId(id);
        return ResponseEntity.ok(response.stream().map(danosMateriaisMapper::toListDto).collect(Collectors.toList()));
    }

    @PostMapping("/{id}/ambientais")
    public ResponseEntity<IdResponseDTO> findAllDanosAmbientaisByOcorrencia(@Valid @RequestBody DanosAmbientaisCreateDTO dano) {
        var response = danosService.saveDanosAmbientais(danosAmbientaisMapper.fromCreatetoEntity(dano));
        return ResponseEntity.ok(idResponseMapper.toDto(response));
    }

    @PostMapping("/{id}/materiais")
    public ResponseEntity<IdResponseDTO> findAllDanosMateriaisByOcorrencia(@Valid @RequestBody DanosMateriaisCreateDTO dano) {
        var response = danosService.saveDanosMateriais(danosMateriaisMapper.fromCreatetoEntity(dano));
        return ResponseEntity.ok(idResponseMapper.toDto(response));
    }

    @PostMapping("/{id}/humanos")
    public ResponseEntity<IdResponseDTO> findAllDanosHumanosByOcorrencia(@Valid @RequestBody DanosHumanosCreateDTO dano) {
        var response = danosService.saveDanosHumanos(danosHumanosMapper.fromCreatetoEntity(dano));
        return ResponseEntity.ok(idResponseMapper.toDto(response));
    }

    @DeleteMapping("/{id}/ambientais/{idAmbiente}")
    public ResponseEntity<Integer> deleteDanosAmbientais(@PathVariable("id") Integer idDano,
                                                         @PathVariable("idAmbiente") Integer idAmbiente) {
        Integer id = danosService.deleteDanosAmbientais(idDano);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}/humanos/{idHumano}")
    public ResponseEntity<Integer> deleteDanosHumanos(@PathVariable("id") Integer idDano,
                                                      @PathVariable("idMaterial") Integer idHumano) {
        Integer id = danosService.deleteDanosHumanos(idDano);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}/materiais/{idMaterial}")
    public ResponseEntity<Integer> deleteDanosMateriais(@PathVariable("id") Integer idDano,
                                                        @PathVariable("idMaterial") Integer idMaterial) {
        Integer id = danosService.deleteDanosMateriais(idDano);
        return ResponseEntity.ok(id);
    }

}
