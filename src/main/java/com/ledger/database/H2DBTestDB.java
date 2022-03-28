package com.ledger.database;

import com.ledger.cobrade.Cobrade;
import com.ledger.cobrade.CobradeRepository;
import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.entities.DanosMateriais;
import com.ledger.danos.repositories.DanosAmbientaisRepository;
import com.ledger.danos.repositories.DanosHumanosRepository;
import com.ledger.danos.repositories.DanosMateriaisRepository;
import com.ledger.ocorrencia.entities.Ocorrencia;
import com.ledger.telefones.entities.Telefone;
import com.ledger.ocorrencia.repositories.OcorrenciaRepository;
import com.ledger.telefones.repositories.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Configuration
public class H2DBTestDB implements CommandLineRunner {
    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private DanosAmbientaisRepository danosAmbientaisRepository;

    @Autowired
    private DanosHumanosRepository danosHumanosRepository;

    @Autowired
    private DanosMateriaisRepository danosMateriaisRepository;

    @Autowired
    private CobradeRepository cobradeRepository;

    @Override
    public void run (String... args) throws Exception{
        /****  COBRADES ****/
        List<Cobrade> cobrades = new ArrayList<Cobrade>();
        Cobrade c1 = Cobrade.builder().categoria("Naturais").grupo("Geológico").subGrupo("Terremoto").tipo("Tremor de terra").subTipo("0").definicao("Vibrações do terreno que provocam" +
                "oscilações verticais e horizontais na superfície" +
                "da Terra (ondas sísmicas). Pode ser natural" +
                "(tectônica) ou induzido (explosões, injeção" +
                "profunda de líquidos e gás, extração de" +
                "fluidos, alívio de carga de minas, enchimento").codigo("11110").build();

        Cobrade c2 = Cobrade.builder().categoria("Naturais").grupo("Geológico").subGrupo("Terremoto").tipo("Tsunami").subTipo("0").definicao("Série de ondas geradas por deslocamento" +
                "de um grande volume de água causado" +
                "geralmente por terremotos, erupções" +
                "vulcânicas ou movimentos de massa").codigo("11120").build();

        cobrades.add(c1);
        cobrades.add(c2);


        /****  Telefones ****/
        List<Telefone> listT1 = new ArrayList<Telefone>();
        List<Telefone> listT2 = new ArrayList<Telefone>();

        Telefone t1 = Telefone.builder().numero("1111111111").build();
        Telefone t2 = Telefone.builder().numero("2222222222").build();

        listT1.add(t1);
        listT2.add(t2);

        /****  Ocorrências ****/
        Ocorrencia o1 = Ocorrencia.builder().dataOcorrencia(new Date()).codCobrade("11110").uf("GO").municipio("Goiânia").build();
        Ocorrencia o2 = Ocorrencia.builder().dataOcorrencia(new Date()).codCobrade("11120").uf("MS").municipio("Barra do Garças").build();

        o1.setInstInformanteTelefones(listT1);
        o2.setInstInformanteTelefones(listT2);

        t1.setOcorrencia(o1);
        t2.setOcorrencia(o2);

        /****  Danos  Humanos****/
        List<DanosHumanos> danosHumanos1 = new ArrayList<DanosHumanos>();
        List<DanosHumanos> danosHumanos2 = new ArrayList<DanosHumanos>();

        DanosHumanos dH1 = DanosHumanos.builder().danoHumanoTipo("Mortos").numeroPessoas(10).build();
        DanosHumanos dH2 = DanosHumanos.builder().danoHumanoTipo("Feridos").numeroPessoas(20).build();

        dH1.setOcorrencia(o1);
        dH2.setOcorrencia(o2);
        danosHumanos1.add(dH1);
        danosHumanos2.add(dH2);

        o1.setDanosHumanos(danosHumanos1);
        o2.setDanosHumanos(danosHumanos2);

        /****  Danos  Ambientais ****/
        List<DanosAmbientais> danosAmbientais1 = new ArrayList<DanosAmbientais>();
        List<DanosAmbientais> danosAmbientais2 = new ArrayList<DanosAmbientais>();

        DanosAmbientais dA1 = DanosAmbientais.builder().danoAmbientalTipo("Contaminação do Ar").populacaoAtingida(47).build();
        DanosAmbientais dA2 = DanosAmbientais.builder().danoAmbientalTipo("Contaminação da Água").populacaoAtingida(23).build();

        dA1.setOcorrencia(o1);
        dA2.setOcorrencia(o2);
        danosAmbientais1.add(dA1);
        danosAmbientais2.add(dA2);

        o1.setDanosAmbientais(danosAmbientais1);
        o2.setDanosAmbientais(danosAmbientais2);

        /****  Danos  Materiais ****/
        List<DanosMateriais> danosMateriais1 = new ArrayList<DanosMateriais>();
        List<DanosMateriais> danosMateriais2 = new ArrayList<DanosMateriais>();

        DanosMateriais dM1 = DanosMateriais.builder().danoMaterialTipo("Unidades Habitacionais").quantidadeDestruida(10).quantidadeDanificada(20).valor(1500.83).build();
        DanosMateriais dM2 = DanosMateriais.builder().danoMaterialTipo("Instalações públicas de saúde").quantidadeDestruida(2).quantidadeDanificada(3).valor(958.45).build();

        dM1.setOcorrencia(o1);
        dM2.setOcorrencia(o2);
        danosMateriais1.add(dM1);
        danosMateriais2.add(dM2);

        o1.setDanosMateriais(danosMateriais1);
        o2.setDanosMateriais(danosMateriais2);

        /**** Saving ****/
        ocorrenciaRepository.saveAll(Arrays.asList(o1,o2));
        telefoneRepository.saveAll(Arrays.asList(t1,t2));
        danosHumanosRepository.saveAll(Arrays.asList(dH1,dH2));
        danosAmbientaisRepository.saveAll(Arrays.asList(dA1,dA2));
        danosMateriaisRepository.saveAll(Arrays.asList(dM1,dM2));
        cobradeRepository.saveAll(cobrades);


    }


}
