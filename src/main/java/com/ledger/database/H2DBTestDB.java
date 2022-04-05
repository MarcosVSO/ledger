package com.ledger.database;

import com.ledger.areasAfetadas.entities.AreaAfetada;
import com.ledger.areasAfetadas.repositories.AreaAfetadaRepository;
import com.ledger.cobrade.Cobrade;
import com.ledger.cobrade.CobradeRepository;
import com.ledger.danos.entities.DanosAmbientais;
import com.ledger.danos.entities.DanosHumanos;
import com.ledger.danos.entities.DanosMateriais;
import com.ledger.danos.entities.tipos.DanosAmbientaisTipo;
import com.ledger.danos.entities.tipos.DanosHumanosTipo;
import com.ledger.danos.entities.tipos.DanosMateriaisTipo;
import com.ledger.danos.repositories.DanosAmbientaisRepository;
import com.ledger.danos.repositories.DanosHumanosRepository;
import com.ledger.danos.repositories.DanosMateriaisRepository;
import com.ledger.danos.repositories.tipos.DanosAmbientaisTipoRepository;
import com.ledger.danos.repositories.tipos.DanosHumanosTipoRepository;
import com.ledger.danos.repositories.tipos.DanosMateriaisTipoRepository;
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

    @Autowired
    private AreaAfetadaRepository areaAfetadaRepository;

    @Autowired
    private DanosAmbientaisTipoRepository danosAmbientaisTipoRepository;

    @Autowired
    private DanosHumanosTipoRepository danosHumanosTipoRepository;

    @Autowired
    private DanosMateriaisTipoRepository danosMateriaisTipoRepository;


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
        Ocorrencia o1 = Ocorrencia.builder().dataOcorrencia(new Date()).codCobrade("11110").uf("GO").municipio("Goiânia").latitude("-16.716666613612432").longitude("-49.252455389736646").instInformadaOrgaoEstadual(true)
                .instituicaoInformadaSedec(true).instInformanteNome("Defesa Civil").instInformanteResponsavel("Fulano").build();
        Ocorrencia o2 = Ocorrencia.builder().dataOcorrencia(new Date()).codCobrade("11120").uf("MS").municipio("Barra do Garças").latitude("-15.894243863201739").longitude("-52.26307143229597").instInformadaOrgaoEstadual(true)
                .instituicaoInformadaSedec(false).build();

        o1.setInstInformanteTelefones(listT1);
        o2.setInstInformanteTelefones(listT2);

        t1.setOcorrencia(o1);
        t2.setOcorrencia(o2);

        /****  Danos  Humanos****/
        List<DanosHumanos> danosHumanos1 = new ArrayList<DanosHumanos>();
        List<DanosHumanos> danosHumanos2 = new ArrayList<DanosHumanos>();

        DanosHumanos dH1 = DanosHumanos.builder().danoHumanoTipo(1).numeroPessoas(10).build();
        DanosHumanos dH2 = DanosHumanos.builder().danoHumanoTipo(2).numeroPessoas(20).build();
        DanosHumanos dH3 = DanosHumanos.builder().danoHumanoTipo(1).numeroPessoas(20).build();

        dH1.setOcorrencia(o1);
        dH2.setOcorrencia(o2);
        dH3.setOcorrencia(o1);
        danosHumanos1.add(dH1);
        danosHumanos1.add(dH3);
        danosHumanos2.add(dH2);

        o1.setDanosHumanos(danosHumanos1);
        o2.setDanosHumanos(danosHumanos2);

        /****  Danos  Ambientais ****/
        List<DanosAmbientais> danosAmbientais1 = new ArrayList<DanosAmbientais>();
        List<DanosAmbientais> danosAmbientais2 = new ArrayList<DanosAmbientais>();

        DanosAmbientais dA1 = DanosAmbientais.builder().danoAmbientalTipo(1).populacaoAtingida(47).build();
        DanosAmbientais dA2 = DanosAmbientais.builder().danoAmbientalTipo(2).populacaoAtingida(23).build();

        dA1.setOcorrencia(o1);
        dA2.setOcorrencia(o2);
        danosAmbientais1.add(dA1);
        danosAmbientais2.add(dA2);

        o1.setDanosAmbientais(danosAmbientais1);
        o2.setDanosAmbientais(danosAmbientais2);

        /****  Danos  Materiais ****/
        List<DanosMateriais> danosMateriais1 = new ArrayList<DanosMateriais>();
        List<DanosMateriais> danosMateriais2 = new ArrayList<DanosMateriais>();

        DanosMateriais dM1 = DanosMateriais.builder().danoMaterialTipo(1).quantidadeDestruida(10).quantidadeDanificada(20).valor(1500.83).build();
        DanosMateriais dM2 = DanosMateriais.builder().danoMaterialTipo(2).quantidadeDestruida(2).quantidadeDanificada(3).valor(958.45).build();

        dM1.setOcorrencia(o1);
        dM2.setOcorrencia(o2);
        danosMateriais1.add(dM1);
        danosMateriais2.add(dM2);

        o1.setDanosMateriais(danosMateriais1);
        o2.setDanosMateriais(danosMateriais2);

        /****  Areas Afetadas ****/
        AreaAfetada a1 = AreaAfetada.builder().residencial("Não Afetada").comercial("Urbana").industrial("Rural").agricola("Urbana e Rural").pecuaria("Urbana e Rural").extrativismoVegetal("Urbana").reservaFlorestal("Rural").mineracao("Não Afetada").turismoOutras("Não Afetada").build();
        AreaAfetada a2 = AreaAfetada.builder().residencial("Urbana").comercial("Urbana").industrial("Rural").agricola("Rural").pecuaria("Rural").extrativismoVegetal("Não Afetada").reservaFlorestal("Urbana").mineracao("Não Afetada").turismoOutras("Não Afetada").build();

        o1.setAreaAfetada(a1);
        o2.setAreaAfetada(a2);

        /**** inserindo tipos danos ****/
        List<DanosAmbientaisTipo> danosAmbientaisTipos = new ArrayList<DanosAmbientaisTipo>();
        List<DanosMateriaisTipo> danosMateriaisTipos = new ArrayList<DanosMateriaisTipo>();
        List<DanosHumanosTipo> danosHumanosTipos = new ArrayList<DanosHumanosTipo>();

        DanosAmbientaisTipo dAT1 = DanosAmbientaisTipo.builder().descricao("contaminacao_ar").build();
        DanosAmbientaisTipo dAT2 = DanosAmbientaisTipo.builder().descricao("contaminacao_agua").build();
        DanosAmbientaisTipo dAT3 = DanosAmbientaisTipo.builder().descricao("contaminacao_solo").build();
        DanosAmbientaisTipo dAT4 = DanosAmbientaisTipo.builder().descricao("exaurimento_hidrico").build();
        DanosAmbientaisTipo dAT5 = DanosAmbientaisTipo.builder().descricao("incendio_apa").build();
        danosAmbientaisTipos.add(dAT1);
        danosAmbientaisTipos.add(dAT2);
        danosAmbientaisTipos.add(dAT3);
        danosAmbientaisTipos.add(dAT4);
        danosAmbientaisTipos.add(dAT5);

        DanosMateriaisTipo dMT1 = DanosMateriaisTipo.builder().descricao("Unidade Habitacionais").build();
        DanosMateriaisTipo dMT2 = DanosMateriaisTipo.builder().descricao("Instalações Públicas de Saúde").build();
        DanosMateriaisTipo dMT3 = DanosMateriaisTipo.builder().descricao("Instalações Públicas de Ensino").build();
        DanosMateriaisTipo dMT4 = DanosMateriaisTipo.builder().descricao("Instalações Públicas Prestadoras de Outros Serviços").build();
        DanosMateriaisTipo dMT5 = DanosMateriaisTipo.builder().descricao("Instalações Públicas Prestadoras de Uso Comunitário").build();
        DanosMateriaisTipo dMT6 = DanosMateriaisTipo.builder().descricao("Obras de Infra Estrutura Públicas").build();
        danosMateriaisTipos.add(dMT1);
        danosMateriaisTipos.add(dMT2);
        danosMateriaisTipos.add(dMT3);
        danosMateriaisTipos.add(dMT4);
        danosMateriaisTipos.add(dMT5);
        danosMateriaisTipos.add(dMT6);

        DanosHumanosTipo dHT1 = DanosHumanosTipo.builder().descricao("Mortos").build();
        DanosHumanosTipo dHT2 = DanosHumanosTipo.builder().descricao("Feridos").build();
        DanosHumanosTipo dHT3 = DanosHumanosTipo.builder().descricao("Enfermos").build();
        DanosHumanosTipo dHT4 = DanosHumanosTipo.builder().descricao("Desabrigados").build();
        DanosHumanosTipo dHT5 = DanosHumanosTipo.builder().descricao("Desalojados").build();
        DanosHumanosTipo dHT6 = DanosHumanosTipo.builder().descricao("Desaparecidos").build();
        DanosHumanosTipo dHT7 = DanosHumanosTipo.builder().descricao("Outros Afetados").build();
        danosHumanosTipos.add(dHT1);
        danosHumanosTipos.add(dHT2);
        danosHumanosTipos.add(dHT3);
        danosHumanosTipos.add(dHT4);
        danosHumanosTipos.add(dHT5);
        danosHumanosTipos.add(dHT6);
        danosHumanosTipos.add(dHT7);


        danosAmbientaisTipoRepository.saveAll(danosAmbientaisTipos);
        danosMateriaisTipoRepository.saveAll(danosMateriaisTipos);
        danosHumanosTipoRepository.saveAll(danosHumanosTipos);

        /**** Saving ****/
        ocorrenciaRepository.saveAll(Arrays.asList(o1,o2));
        telefoneRepository.saveAll(Arrays.asList(t1,t2));
        danosHumanosRepository.saveAll(Arrays.asList(dH1,dH2,dH3));
        danosAmbientaisRepository.saveAll(Arrays.asList(dA1,dA2));
        danosMateriaisRepository.saveAll(Arrays.asList(dM1,dM2));
        cobradeRepository.saveAll(cobrades);
        areaAfetadaRepository.saveAll(Arrays.asList(a1,a2));


    }


}
