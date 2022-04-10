package com.ledger.danos.rest;

import com.ledger.danos.dtos.DanoCreateDTO;
import com.ledger.danos.dtos.DanoDetailsDTO;
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
import java.util.Optional;

@RestController
@RequestMapping(value = "/danos")
@Validated
public class DanosRest {

    private DanosService danosService;
    private DanosAmbientaisMapper danosAmbientaisMapper;
    private DanosHumanosMapper danosHumanosMapper;
    private DanosMateriaisMapper danosMateriaisMapper;
    private DanoMapper danoMapper;
    private IdResponseMapper idResponseMapper;

    @Autowired
    public DanosRest(DanosService danosService, DanosAmbientaisMapper danosAmbientaisMapper,
                     DanosHumanosMapper danosHumanosMapper, DanosMateriaisMapper danosMateriaisMapper,
                     DanoMapper danoMapper, IdResponseMapper idResponseMapper) {
        this.danosService = danosService;
        this.danosAmbientaisMapper = danosAmbientaisMapper;
        this.danosHumanosMapper = danosHumanosMapper;
        this.danoMapper = danoMapper;
        this.danosMateriaisMapper = danosMateriaisMapper;
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
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .contentType(MediaType.valueOf(file.getContentType()))
                .body(response.get().getData());
    }

    @GetMapping("/ambientais")
    public ResponseEntity<List<DanosAmbientais>> findAllDanosAmbientais() {
        var response = danosService.findAllDanosAmbientais();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/humanos")
    public ResponseEntity<List<DanosHumanos>> findAllDanosHumanos() {
        var response = danosService.findAllDanosHumanos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/materiais")
    public ResponseEntity<List<DanosMateriais>> findAllDanosMateriais() {
        var response = danosService.findAllDanosMateriais();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ambientais/{idDano}")
    public ResponseEntity<DanosAmbientais> findDanosAmbientais(@PathVariable Integer idDano) {
        Optional<DanosAmbientais> danosAmbientais = danosService.findDanosAmbientais(idDano);
        return ResponseEntity.of(danosAmbientais);
    }

    @GetMapping("/humanos/{idDano}")
    public ResponseEntity<DanosHumanos> findDanosHumanos(@PathVariable Integer idDano) {
        Optional<DanosHumanos> danosHumanos = danosService.findDanosHumanos(idDano);
        return ResponseEntity.of(danosHumanos);
    }

    @GetMapping("/materiais/{idDano}")
    public ResponseEntity<DanosMateriais> findDanosMateriais(@PathVariable Integer idDano) {
        Optional<DanosMateriais> danosMateriais = danosService.findDanosMateriais(idDano);
        return ResponseEntity.of(danosMateriais);
    }

    @DeleteMapping("/ambientais/{idDano}")
    public ResponseEntity<Integer> deleteDanosAmbientais(@PathVariable Integer idDano) {
        Integer id = danosService.deleteDanosAmbientais(idDano);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/humanos/{idDano}")
    public ResponseEntity<Integer> deleteDanosHumanos(@PathVariable Integer idDano) {
        Integer id = danosService.deleteDanosHumanos(idDano);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/materiais/{idDano}")
    public ResponseEntity<Integer> deleteDanosMateriais(@PathVariable Integer idDano) {
        Integer id = danosService.deleteDanosMateriais(idDano);
        return ResponseEntity.ok(id);
    }

    @PostMapping("ambientais")
    public ResponseEntity<IdResponseDTO> findAllDanosAmbientaisByOcorrencia(@Valid @RequestBody DanosAmbientaisCreateDTO dano) {
        var response = danosService.saveDanosAmbientais(danosAmbientaisMapper.fromCreatetoEntity(dano));
        return ResponseEntity.ok(idResponseMapper.toDto(response));
    }

    @PostMapping("materiais")
    public ResponseEntity<IdResponseDTO> findAllDanosMateriaisByOcorrencia(@Valid @RequestBody DanosMateriaisCreateDTO dano) {
        var response = danosService.saveDanosMateriais(danosMateriaisMapper.fromCreatetoEntity(dano));
        return ResponseEntity.ok(idResponseMapper.toDto(response));
    }

    @PostMapping("humanos")
    public ResponseEntity<IdResponseDTO> findAllDanosHumanosByOcorrencia(@Valid @RequestBody DanosHumanosCreateDTO dano) {
        var response = danosService.saveDanosHumanos(danosHumanosMapper.fromCreatetoEntity(dano));
        return ResponseEntity.ok(idResponseMapper.toDto(response));
    }

}
