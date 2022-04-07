package com.ledger.localidades.rest;

import com.ledger.localidades.dtos.EstadoDTO;
import com.ledger.localidades.dtos.MunicipioDTO;
import com.ledger.localidades.service.LocalidadeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/estados")
public class LocalidadeRest {
    @Autowired
    LocalidadeService localidadeService;

    @GetMapping()
    public ResponseEntity<List<EstadoDTO>> findAllEstados(){
        return new ResponseEntity<List<EstadoDTO>>(localidadeService.findAllEstados(), HttpStatus.OK);
    }

    @GetMapping("/{id}/municipios")
    public ResponseEntity<List<MunicipioDTO>> findMunicipiosByUF (@PathVariable("id") Integer id){
        return new ResponseEntity<List<MunicipioDTO>>(localidadeService.findMunicipiosByUF(id), HttpStatus.OK);
    }
}
