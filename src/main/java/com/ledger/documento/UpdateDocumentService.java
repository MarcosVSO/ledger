package com.ledger.documento;


import com.ledger.cobrade.CobradeService;
import com.ledger.ocorrencia.dto.FideDTO;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class UpdateDocumentService {

    @Autowired
    private CobradeService cobradeService;

    public byte[] updateDocument (FideDTO fideDTO){
        try{
        String input = System.getProperty("user.dir")+"\\src\\main\\resources\\docTemplate\\FIDE.docx";
        String output = System.getProperty("user.dir")+"\\src\\main\\resources\\docOutput\\output.docx";

        XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(input)));

            LocalDateTime localDate = LocalDateTime.ofInstant(fideDTO.getDataOcorrencia().toInstant(), ZoneId.of("America/Sao_Paulo"));

            Map<String, String> replacements = new LinkedHashMap<String,String>();
            //Dados da ocorrência
            replacements.put("${uf}",fideDTO.getUf());
            replacements.put("${municipio}",fideDTO.getMunicipio());
            replacements.put("${cobrade}",fideDTO.getCodCobrade());
            replacements.put("${cobradeDescricao}", cobradeService.findByCodigo(fideDTO.getCodCobrade()).getSubGrupo());
            replacements.put("${oc_dia}", String.valueOf(localDate.getDayOfMonth()) );
            replacements.put("${oc_mes}", convertEngToPt(String.valueOf(localDate.getMonth())));
            replacements.put("${oc_ano}", String.valueOf(localDate.getYear()));
            replacements.put("${oc_hr}", localDate.getHour()+":"+localDate.getMinute());
            //  Danos Humanos, Materiais ou Ambientais
            replacements.put("${mortos}",fideDTO.getDanosHumanosMapped().get("Mortos").toString());
            replacements.put("${feridos}",fideDTO.getDanosHumanosMapped().get("Feridos").toString());
            replacements.put("${enfermos}",fideDTO.getDanosHumanosMapped().get("Enfermos").toString());
            replacements.put("${desabrigados}",fideDTO.getDanosHumanosMapped().get("Desabrigados").toString());
            replacements.put("${desalojados}",fideDTO.getDanosHumanosMapped().get("Desalojados").toString());
            replacements.put("${desaparecidos}",fideDTO.getDanosHumanosMapped().get("Desaparecidos").toString());
            replacements.put("${outrosAfetados}",fideDTO.getDanosHumanosMapped().get("Outros Afetados").toString());
            // Danos Materiais
            replacements.put("${hab_dest}",fideDTO.getDanosMateriaisSoma().get(0).getQuantidadeDestruida().toString());
            replacements.put("${hab_dani}",fideDTO.getDanosMateriaisSoma().get(0).getQuantidadeDanificada().toString());
            replacements.put("${hab_valor}",fideDTO.getDanosMateriaisSoma().get(0).getValor().toString());
            replacements.put("${saude_dest}",fideDTO.getDanosMateriaisSoma().get(1).getQuantidadeDestruida().toString());
            replacements.put("${saude_dani}",fideDTO.getDanosMateriaisSoma().get(1).getQuantidadeDanificada().toString());
            replacements.put("${saude_valor}",fideDTO.getDanosMateriaisSoma().get(1).getValor().toString());
            replacements.put("${ensino_dest}",fideDTO.getDanosMateriaisSoma().get(2).getQuantidadeDestruida().toString());
            replacements.put("${ensino_dani}",fideDTO.getDanosMateriaisSoma().get(2).getQuantidadeDanificada().toString());
            replacements.put("${ensino_valor}",fideDTO.getDanosMateriaisSoma().get(2).getValor().toString());
            replacements.put("${servicos_dest}",fideDTO.getDanosMateriaisSoma().get(3).getQuantidadeDestruida().toString());
            replacements.put("${servicos_dano}",fideDTO.getDanosMateriaisSoma().get(3).getQuantidadeDanificada().toString());
            replacements.put("${servicos_valor}",fideDTO.getDanosMateriaisSoma().get(3).getValor().toString());
            replacements.put("${comu_dest}",fideDTO.getDanosMateriaisSoma().get(4).getQuantidadeDestruida().toString());
            replacements.put("${comu_dani}",fideDTO.getDanosMateriaisSoma().get(4).getQuantidadeDanificada().toString());
            replacements.put("${comu_valor}",fideDTO.getDanosMateriaisSoma().get(4).getValor().toString());
            replacements.put("${infra_dest}",fideDTO.getDanosMateriaisSoma().get(5).getQuantidadeDestruida().toString());
            replacements.put("${infra_dani}",fideDTO.getDanosMateriaisSoma().get(5).getQuantidadeDanificada().toString());
            replacements.put("${infra_valor}",fideDTO.getDanosMateriaisSoma().get(5).getValor().toString());
            // Danos Ambientais
            replacements.put("${ambiental_ar}",fideDTO.getDanosAmbientaisMapped().get("Contaminação do Ar").toString());
            replacements.put("${ambiental_agua}",fideDTO.getDanosAmbientaisMapped().get("Contaminação do Água").toString());
            replacements.put("${ambiental_solo}",fideDTO.getDanosAmbientaisMapped().get("Contaminação do Solo").toString());
            replacements.put("${ambiental_hidrico}",fideDTO.getDanosAmbientaisMapped().get("Exaurimento Hídrico").toString());
            replacements.put("${ambiental_apa}",fideDTO.getDanosAmbientaisMapped().get("Incêndio APA").toString());
            // Areas afetadas
            replacements.put("${area_afetada_residencial}",fideDTO.getAreaAfetada().getResidencial());
            replacements.put("${area_afetada_comercial}",fideDTO.getAreaAfetada().getComercial());
            replacements.put("${area_afetada_industrial}",fideDTO.getAreaAfetada().getIndustrial());
            replacements.put("${area_afetada_agricola}",fideDTO.getAreaAfetada().getAgricola());
            replacements.put("${area_afetada_pecuaria}",fideDTO.getAreaAfetada().getPecuaria());
            replacements.put("${area_afetada_extratVegetal}",fideDTO.getAreaAfetada().getExtrativismoVegetal());
            replacements.put("${area_afetada_apa}",fideDTO.getAreaAfetada().getReservaFlorestal());
            replacements.put("${area_afetada_mineracao}",fideDTO.getAreaAfetada().getMineracao());
            replacements.put("${area_afetada_turismo}",fideDTO.getAreaAfetada().getTurismoOutras());
            //instituições
            replacements.put("${inst_informante}",fideDTO.getInstInformanteNome());
            replacements.put("${inst_responsavel}",fideDTO.getInstInformanteResponsavel());
            replacements.put("${inst_telefones}",fideDTO.getTelefoneNums());
            replacements.put("${inf_defesa}",fideDTO.getInstInformadaOrgaoEstadual());
            replacements.put("${inf_sedec}",fideDTO.getInstituicaoInformanteSedec());


            List<XWPFTable> tables = doc.getTables();
            for (XWPFTable table : tables){
                for (XWPFTableRow tableRow : table.getRows()){
                    for (XWPFTableCell tableCell : tableRow.getTableCells()){
                        for (XWPFParagraph cellParagraph : tableCell.getParagraphs()){
                            for (XWPFRun xwpfRun : cellParagraph.getRuns()) {
                                String xwpfRunText = xwpfRun.getText(xwpfRun.getTextPosition());
                                for (Map.Entry<String, String> entry : replacements.entrySet()) {
                                    if (xwpfRunText != null && xwpfRunText.contains(entry.getKey())) {
                                        xwpfRunText = xwpfRunText.replace(entry.getKey(), entry.getValue());
                                    }
                                }
                                xwpfRun.setText(xwpfRunText, 0);
                            }
                        }
                    }
                }
            }

            try (FileOutputStream out = new FileOutputStream(output)) {
                doc.write(out);
            }

            FileInputStream fis = new FileInputStream(output);
            return IOUtils.toByteArray(fis);


        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String convertEngToPt(String month){
        Map<String,String> mappedMonth = new HashMap<String,String>();
        mappedMonth.put("JANUARY","JANEIRO");
        mappedMonth.put("FEBRUARY","FEVEREIRO");
        mappedMonth.put("MARCH","MARÇO");
        mappedMonth.put("APRIL","ABRIL");
        mappedMonth.put("MAY","MAIO");
        mappedMonth.put("JUNE","JUNHO");
        mappedMonth.put("JULY","JULHO");
        mappedMonth.put("AUGUST","AGOSTO");
        mappedMonth.put("SEPTEMBER","SETEMBRO");
        mappedMonth.put("OCTOBER","OUTUBRO");
        mappedMonth.put("NOVEMBER","NOVEMBRO");
        mappedMonth.put("DECEMBER","DEZEMBRO");

        return mappedMonth.get(month);
    }
}
