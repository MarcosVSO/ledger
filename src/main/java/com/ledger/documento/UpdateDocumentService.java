package com.ledger.documento;


import com.ledger.ocorrencia.dto.FideDTO;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UpdateDocumentService {

    @Value("classpath:docTemplate/FIDE.docx")
    private Resource fideTemplate;

    public byte[] updateDocument(FideDTO fideDTO) {
        try {
            XWPFDocument doc = new XWPFDocument(fideTemplate.getInputStream());

            LocalDateTime localDate = LocalDateTime.ofInstant(fideDTO.getDadosOcorrencia().getData().toInstant(),
                    ZoneId.of("America/Sao_Paulo"));

            Map<String, String> replacements = new LinkedHashMap<String, String>();
            //Dados da ocorrência
            replacements.put("${uf}", fideDTO.getDadosOcorrencia().getUf().getSigla());
            replacements.put("${municipio}", fideDTO.getDadosOcorrencia().getMunicipio().getNome());
            replacements.put("${cobrade}", fideDTO.getDadosOcorrencia().getCobrade().getCodigo());
            replacements.put("${cobradeDescricao}", fideDTO.getDadosOcorrencia().getCobrade().getCategoria());
            replacements.put("${oc_dia}", String.valueOf(localDate.getDayOfMonth()));
            replacements.put("${oc_mes}", convertEngToPt(String.valueOf(localDate.getMonth())));
            replacements.put("${oc_ano}", String.valueOf(localDate.getYear()));
            replacements.put("${oc_hr}", localDate.getHour() + ":" + localDate.getMinute());
            //  Danos Humanos, Materiais ou Ambientais
            replacements.put("${mortos}", fideDTO.getDanosHumanosMapped().get("Mortos").toString());
            replacements.put("${feridos}", fideDTO.getDanosHumanosMapped().get("Feridos").toString());
            replacements.put("${enfermos}", fideDTO.getDanosHumanosMapped().get("Enfermos").toString());
            replacements.put("${desabrigados}", fideDTO.getDanosHumanosMapped().get("Desabrigados").toString());
            replacements.put("${desalojados}", fideDTO.getDanosHumanosMapped().get("Desalojados").toString());
            replacements.put("${desaparecidos}", fideDTO.getDanosHumanosMapped().get("Desaparecidos").toString());
            replacements.put("${outrosAfetados}", fideDTO.getDanosHumanosMapped().get("Outros Afetados").toString());
            // Danos Materiais
            replacements.put("${hab_dest}", fideDTO.getDanosMateriaisSoma().get(0).getQuantidadeDestruida().toString());
            replacements.put("${hab_dani}",
                    fideDTO.getDanosMateriaisSoma().get(0).getQuantidadeDanificada().toString());
            replacements.put("${hab_valor}",
                    String.valueOf(fideDTO.getDanosMateriaisSoma().get(0).getValorDanificado() + fideDTO.getDanosMateriaisSoma().get(0).getValorDestruido()));
            replacements.put("${saude_dest}",
                    fideDTO.getDanosMateriaisSoma().get(1).getQuantidadeDestruida().toString());
            replacements.put("${saude_dani}",
                    fideDTO.getDanosMateriaisSoma().get(1).getQuantidadeDanificada().toString());
            replacements.put("${saude_valor}",
                    String.valueOf(fideDTO.getDanosMateriaisSoma().get(1).getValorDanificado() + fideDTO.getDanosMateriaisSoma().get(1).getValorDestruido()));
            replacements.put("${ensino_dest}",
                    fideDTO.getDanosMateriaisSoma().get(2).getQuantidadeDestruida().toString());
            replacements.put("${ensino_dani}",
                    fideDTO.getDanosMateriaisSoma().get(2).getQuantidadeDanificada().toString());
            replacements.put("${ensino_valor}",
                    String.valueOf(fideDTO.getDanosMateriaisSoma().get(2).getValorDanificado() + fideDTO.getDanosMateriaisSoma().get(2).getValorDestruido()));
            replacements.put("${servicos_dest}",
                    fideDTO.getDanosMateriaisSoma().get(3).getQuantidadeDestruida().toString());
            replacements.put("${servicos_dano}",
                    fideDTO.getDanosMateriaisSoma().get(3).getQuantidadeDanificada().toString());
            replacements.put("${servicos_valor}",
                    String.valueOf(fideDTO.getDanosMateriaisSoma().get(3).getValorDanificado() + fideDTO.getDanosMateriaisSoma().get(3).getValorDestruido()));
            replacements.put("${comu_dest}",
                    fideDTO.getDanosMateriaisSoma().get(4).getQuantidadeDestruida().toString());
            replacements.put("${comu_dani}",
                    fideDTO.getDanosMateriaisSoma().get(4).getQuantidadeDanificada().toString());
            replacements.put("${comu_valor}",
                    String.valueOf(fideDTO.getDanosMateriaisSoma().get(4).getValorDanificado() + fideDTO.getDanosMateriaisSoma().get(4).getValorDestruido()));
            replacements.put("${infra_dest}",
                    fideDTO.getDanosMateriaisSoma().get(5).getQuantidadeDestruida().toString());
            replacements.put("${infra_dani}",
                    fideDTO.getDanosMateriaisSoma().get(5).getQuantidadeDanificada().toString());
            replacements.put("${infra_valor}",
                    String.valueOf(fideDTO.getDanosMateriaisSoma().get(5).getValorDanificado() + fideDTO.getDanosMateriaisSoma().get(5).getValorDestruido()));
            // Danos Ambientais
            replacements.put("${ambiental_ar}",
                    fideDTO.getDanosAmbientaisMapped().get("Contaminação do Ar").toString());
            replacements.put("${ambiental_agua}",
                    fideDTO.getDanosAmbientaisMapped().get("Contaminação do Água").toString());
            replacements.put("${ambiental_solo}",
                    fideDTO.getDanosAmbientaisMapped().get("Contaminação do Solo").toString());
            replacements.put("${ambiental_hidrico}",
                    fideDTO.getDanosAmbientaisMapped().get("Exaurimento Hídrico").toString());
            replacements.put("${ambiental_apa}", fideDTO.getDanosAmbientaisMapped().get("Incêndio APA").toString());
            // Areas afetadas
            replacements.put("${area_afetada_residencial}",
                    fideDTO.getDadosOcorrencia().getAreaAfetada().getResidencial());
            replacements.put("${area_afetada_comercial}", fideDTO.getDadosOcorrencia().getAreaAfetada().getComercial());
            replacements.put("${area_afetada_industrial}",
                    fideDTO.getDadosOcorrencia().getAreaAfetada().getIndustrial());
            replacements.put("${area_afetada_agricola}", fideDTO.getDadosOcorrencia().getAreaAfetada().getAgricola());
            replacements.put("${area_afetada_pecuaria}", fideDTO.getDadosOcorrencia().getAreaAfetada().getPecuaria());
            replacements.put("${area_afetada_extratVegetal}",
                    fideDTO.getDadosOcorrencia().getAreaAfetada().getExtrativismoVegetal());
            replacements.put("${area_afetada_apa}",
                    fideDTO.getDadosOcorrencia().getAreaAfetada().getReservaFlorestal());
            replacements.put("${area_afetada_mineracao}", fideDTO.getDadosOcorrencia().getAreaAfetada().getMineracao());
            replacements.put("${area_afetada_turismo}",
                    fideDTO.getDadosOcorrencia().getAreaAfetada().getTurismoOutras());
            //instituições
            replacements.put("${inst_informante}", fideDTO.getDadosOcorrencia().getInstituicaoInformante().getNome());
            replacements.put("${inst_responsavel}",
                    fideDTO.getDadosOcorrencia().getInstituicaoInformante().getResponsavel());
            replacements.put("${inst_telefones}",
                    fideDTO.getDadosOcorrencia().getInstituicaoInformante().getTelefonesString());
            replacements.put("${inf_defesa}", fideDTO.getInstInformadaOrgaoEstadual());
            replacements.put("${inf_sedec}", fideDTO.getInstituicaoInformanteSedec());


            List<XWPFTable> tables = doc.getTables();
            for (XWPFTable table : tables) {
                for (XWPFTableRow tableRow : table.getRows()) {
                    for (XWPFTableCell tableCell : tableRow.getTableCells()) {
                        for (XWPFParagraph cellParagraph : tableCell.getParagraphs()) {
                            for (XWPFRun xwpfRun : cellParagraph.getRuns()) {
                                String xwpfRunText = xwpfRun.getText(xwpfRun.getTextPosition());
                                for (Map.Entry<String, String> entry : replacements.entrySet()) {
                                    if (xwpfRunText != null && xwpfRunText.contains(entry.getKey())) {
                                        System.out.println(entry.getKey() + entry.getValue());
                                        xwpfRunText = xwpfRunText.replace(entry.getKey(), entry.getValue());
                                    }
                                }
                                xwpfRun.setText(xwpfRunText, 0);
                            }
                        }
                    }
                }
            }


            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try (bos) {
                doc.write(bos);
            }

            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String convertEngToPt(String month) {
        Map<String, String> mappedMonth = new HashMap<>();
        mappedMonth.put("JANUARY", "JANEIRO");
        mappedMonth.put("FEBRUARY", "FEVEREIRO");
        mappedMonth.put("MARCH", "MARÇO");
        mappedMonth.put("APRIL", "ABRIL");
        mappedMonth.put("MAY", "MAIO");
        mappedMonth.put("JUNE", "JUNHO");
        mappedMonth.put("JULY", "JULHO");
        mappedMonth.put("AUGUST", "AGOSTO");
        mappedMonth.put("SEPTEMBER", "SETEMBRO");
        mappedMonth.put("OCTOBER", "OUTUBRO");
        mappedMonth.put("NOVEMBER", "NOVEMBRO");
        mappedMonth.put("DECEMBER", "DEZEMBRO");

        return mappedMonth.get(month);
    }
}
