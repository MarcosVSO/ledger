package com.ledger.database;

import com.ledger.areasAfetadas.repositories.AreaAfetadaRepository;
import com.ledger.cobrade.repository.CobradeRepository;
import com.ledger.danos.repositories.DanosAmbientaisRepository;
import com.ledger.danos.repositories.DanosHumanosRepository;
import com.ledger.danos.repositories.DanosMateriaisRepository;
import com.ledger.danos.repositories.tipos.DanosTipoRepository;
import com.ledger.ocorrencia.repositories.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


@Configuration
public class H2DBTestDB implements CommandLineRunner {
    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

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
    private DanosTipoRepository danosTipoRepository;


    @Override
    public void run (String... args) throws Exception{
//        /****  COBRADES ****/
//        List<Cobrade> cobrades = new ArrayList<Cobrade>();
//        Cobrade c1 = Cobrade.builder().categoria("Naturais").grupo("Geológico").subGrupo("Terremoto").tipo("Tremor de terra").subTipo("0").definicao("Vibrações do terreno que provocam" +
//                "oscilações verticais e horizontais na superfície" +
//                "da Terra (ondas sísmicas). Pode ser natural" +
//                "(tectônica) ou induzido (explosões, injeção" +
//                "profunda de líquidos e gás, extração de" +
//                "fluidos, alívio de carga de minas, enchimento").codigo("11110").build();
//
//        Cobrade c2 = Cobrade.builder().categoria("Naturais").grupo("Geológico").subGrupo("Terremoto").tipo("Tsunami").subTipo("0").definicao("Série de ondas geradas por deslocamento" +
//                "de um grande volume de água causado" +
//                "geralmente por terremotos, erupções" +
//                "vulcânicas ou movimentos de massa").codigo("11120").build();
//
//        cobrades.add(c1);
//        cobrades.add(c2);
//
//
//        /****  Telefones ****/
//        List<Telefone> listT1 = new ArrayList<Telefone>();
//        List<Telefone> listT2 = new ArrayList<Telefone>();
//
//        Telefone t1 = Telefone.builder().numero("1111111111").build();
//        Telefone t2 = Telefone.builder().numero("2222222222").build();
//
//        listT1.add(t1);
//        listT2.add(t2);
//
//        /****  Ocorrências ****/
//        Ocorrencia o1 = Ocorrencia.builder().dataOcorrencia(new Date()).codCobrade("11110").uf("52")
//                .municipio("5208707").latitude("-16.716666613612432").longitude("-49.252455389736646").instInformadaOrgaoEstadual(true)
//                .instituicaoInformadaSedec(true).instInformanteNome("Defesa Civil").instInformanteResponsavel("Fulano").build();
//        Ocorrencia o2 = Ocorrencia.builder().dataOcorrencia(new Date()).codCobrade("11120").uf("50").municipio("5101803").latitude("-15.894243863201739").longitude("-52.26307143229597").instInformadaOrgaoEstadual(true)
//                .instituicaoInformadaSedec(false).instInformanteNome("Defesa Civil").instInformanteResponsavel("Fulano").build();
//
//        o1.setInstInformanteTelefones(listT1);
//        o2.setInstInformanteTelefones(listT2);
//
//        t1.setOcorrencia(o1);
//        t2.setOcorrencia(o2);
//
//        /****  Danos  Humanos****/
//        List<DanosHumanos> danosHumanos1 = new ArrayList<>();
//        List<DanosHumanos> danosHumanos2 = new ArrayList<>();
//
//        DanosHumanos dH1 = DanosHumanos.builder().danoHumanoTipo(12).numeroPessoas(10).build();
//        DanosHumanos dH2 = DanosHumanos.builder().danoHumanoTipo(13).numeroPessoas(20).build();
//        DanosHumanos dH3 = DanosHumanos.builder().danoHumanoTipo(14).numeroPessoas(20).build();
//
//        dH1.setOcorrencia(o1);
//        dH2.setOcorrencia(o2);
//        dH3.setOcorrencia(o1);
//        danosHumanos1.add(dH1);
//        danosHumanos1.add(dH3);
//        danosHumanos2.add(dH2);
//
//        o1.setDanosHumanos(danosHumanos1);
//        o2.setDanosHumanos(danosHumanos2);
//
//        /****  Danos  Ambientais ****/
//        List<DanosAmbientais> danosAmbientais1 = new ArrayList<DanosAmbientais>();
//        List<DanosAmbientais> danosAmbientais2 = new ArrayList<DanosAmbientais>();
//
//        DanosAmbientais dA1 = DanosAmbientais.builder().tipo(1).populacaoAtingida(47).build();
//        DanosAmbientais dA2 = DanosAmbientais.builder().tipo(2).populacaoAtingida(23).build();
//
//        danosAmbientais1.add(dA1);
//        danosAmbientais2.add(dA2);
//
//        o1.setDanosAmbientais(danosAmbientais1);
//        o2.setDanosAmbientais(danosAmbientais2);
//
//        /****  Danos  Materiais ****/
//        List<DanosMateriais> danosMateriais1 = new ArrayList<DanosMateriais>();
//        List<DanosMateriais> danosMateriais2 = new ArrayList<DanosMateriais>();
//
//        DanosMateriais dM1 =
//                DanosMateriais.builder().danoMaterialTipo(6).quantidadeDestruida(10).quantidadeDanificada(20).valor(BigInteger.valueOf(150083)).build();
//        DanosMateriais dM2 =
//                DanosMateriais.builder().danoMaterialTipo(7).quantidadeDestruida(2).quantidadeDanificada(3).valor(BigInteger.valueOf(95845)).build();
//
//        dM1.setOcorrencia(o1);
//        dM2.setOcorrencia(o2);
//        danosMateriais1.add(dM1);
//        danosMateriais2.add(dM2);
//
//        o1.setDanosMateriais(danosMateriais1);
//        o2.setDanosMateriais(danosMateriais2);
//
//        /****  Areas Afetadas ****/
//        AreaAfetada a1 = AreaAfetada.builder().residencial("Não Afetada").comercial("Urbana").industrial("Rural").agricola("Urbana e Rural").pecuaria("Urbana e Rural").extrativismoVegetal("Urbana").reservaFlorestal("Rural").mineracao("Não Afetada").turismoOutras("Não Afetada").build();
//        AreaAfetada a2 = AreaAfetada.builder().residencial("Urbana").comercial("Urbana").industrial("Rural").agricola("Rural").pecuaria("Rural").extrativismoVegetal("Não Afetada").reservaFlorestal("Urbana").mineracao("Não Afetada").turismoOutras("Não Afetada").build();
//
//        o1.setAreaAfetada(a1);
//        o2.setAreaAfetada(a2);
//
//        /**** inserindo tipos danos ****/
//        List<Tipo> tipos = new ArrayList<>();
//
//        Tipo dAT1 = Tipo.builder().categoria("ambiental").descricao("Contaminação do Ar").build();
//        Tipo dAT2 = Tipo.builder().categoria("ambiental").descricao("Contaminação do Água").build();
//        Tipo dAT3 = Tipo.builder().categoria("ambiental").descricao("Contaminação do Solo").build();
//        Tipo dAT4 = Tipo.builder().categoria("ambiental").descricao("Exaurimento Hídrico").build();
//        Tipo dAT5 = Tipo.builder().categoria("ambiental").descricao("Incêndio APA").build();
//        tipos.add(dAT1);
//        tipos.add(dAT2);
//        tipos.add(dAT3);
//        tipos.add(dAT4);
//        tipos.add(dAT5);
//
//        Tipo dMT1 = Tipo.builder().categoria("material").descricao("Unidade Habitacionais").build();
//        Tipo dMT2 = Tipo.builder().categoria("material").descricao("Instalações Públicas de Saúde").build();
//        Tipo dMT3 = Tipo.builder().categoria("material").descricao("Instalações Públicas de Ensino").build();
//        Tipo dMT4 = Tipo.builder().categoria("material").descricao("Instalações Públicas Prestadoras de Outros" +
//                " Serviços").build();
//        Tipo dMT5 = Tipo.builder().categoria("material").descricao("Instalações Públicas Prestadoras de Uso " +
//                "Comunitário").build();
//        Tipo dMT6 = Tipo.builder().categoria("material").descricao("Obras de Infra Estrutura Públicas").build();
//        tipos.add(dMT1);
//        tipos.add(dMT2);
//        tipos.add(dMT3);
//        tipos.add(dMT4);
//        tipos.add(dMT5);
//        tipos.add(dMT6);
//
//        Tipo dHT1 = Tipo.builder().categoria("humano").descricao("Mortos").build();
//        Tipo dHT2 = Tipo.builder().categoria("humano").descricao("Feridos").build();
//        Tipo dHT3 = Tipo.builder().categoria("humano").descricao("Enfermos").build();
//        Tipo dHT4 = Tipo.builder().categoria("humano").descricao("Desabrigados").build();
//        Tipo dHT5 = Tipo.builder().categoria("humano").descricao("Desalojados").build();
//        Tipo dHT6 = Tipo.builder().categoria("humano").descricao("Desaparecidos").build();
//        Tipo dHT7 = Tipo.builder().categoria("humano").descricao("Outros Afetados").build();
//        tipos.add(dHT1);
//        tipos.add(dHT2);
//        tipos.add(dHT3);
//        tipos.add(dHT4);
//        tipos.add(dHT5);
//        tipos.add(dHT6);
//        tipos.add(dHT7);
//
//        /**** Saving ****/
//        danosTipoRepository.saveAll(tipos);
//        ocorrenciaRepository.saveAll(Arrays.asList(o1,o2));
//        telefoneRepository.saveAll(Arrays.asList(t1,t2));
//        danosHumanosRepository.saveAll(Arrays.asList(dH1,dH2,dH3));
//        danosAmbientaisRepository.saveAll(Arrays.asList(dA1,dA2));
//        danosMateriaisRepository.saveAll(Arrays.asList(dM1,dM2));
//        cobradeRepository.saveAll(cobrades);
//        areaAfetadaRepository.saveAll(Arrays.asList(a1,a2));


    }


}
