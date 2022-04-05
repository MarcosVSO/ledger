package com.ledger.danos.rest;

import com.ledger.danos.dtos.IdResponseDTO;
import com.ledger.danos.dtos.create.DanosAmbientaisCreateDTO;
import com.ledger.danos.dtos.create.DanosHumanosCreateDTO;
import com.ledger.danos.dtos.create.DanosMateriaisCreateDTO;
import com.ledger.danos.dtos.mappers.DanosAmbientaisDTOMapper;
import com.ledger.danos.dtos.mappers.DanosHumanosDTOMapper;
import com.ledger.danos.dtos.mappers.DanosMateriaisDTOMapper;
import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.entities.DanosMateriais;
import com.ledger.danos.service.DanosService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private DanosService danosService;

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

    @PostMapping("ambientais")
    public ResponseEntity<IdResponseDTO> findAllDanosAmbientaisByOcorrencia(@Valid @RequestBody DanosAmbientaisCreateDTO dano) {
        var response = danosService.saveDanosAmbientais(DanosAmbientaisDTOMapper.toEntity(dano));
        return ResponseEntity.ok(IdResponseDTO.from(response));
    }

    @PostMapping("materiais")
    public ResponseEntity<IdResponseDTO> findAllDanosMateriaisByOcorrencia(@Valid @RequestBody DanosMateriaisCreateDTO dano) {
        var response = danosService.saveDanosMateriais(DanosMateriaisDTOMapper.toEntity(dano));
        return ResponseEntity.ok(IdResponseDTO.from(response));
    }

    @PostMapping("humanos")
    public ResponseEntity<IdResponseDTO> findAllDanosHumanosByOcorrencia(@Valid @RequestBody DanosHumanosCreateDTO dano) {
        var response = danosService.saveDanosHumanos(DanosHumanosDTOMapper.toEntity(dano));
        return ResponseEntity.ok(IdResponseDTO.from(response));
    }

}
