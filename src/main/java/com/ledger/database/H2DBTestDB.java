package com.ledger.database;

import com.ledger.ocorrencia.entities.Ocorrencia;
import com.ledger.ocorrencia.repositories.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Date;


@Configuration
public class H2DBTestDB implements CommandLineRunner {
    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Override
    public void run (String... args) throws Exception{
        Ocorrencia o1 = Ocorrencia.builder().dataOcorrencia(new Date()).idCobrade("11110").uf("GO").municipio("Goiânia").build();
        Ocorrencia o2 = Ocorrencia.builder().dataOcorrencia(new Date()).idCobrade("11120").uf("MS").municipio("Barra do Garças").build();
        ocorrenciaRepository.saveAll(Arrays.asList(o1,o2));
    }


}
