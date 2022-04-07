package com.ledger.localidades.service;

import com.ledger.localidades.dtos.EstadoDTO;
import com.ledger.localidades.dtos.MunicipioDTO;
import com.ledger.ocorrencia.dto.FideDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class LocalidadeService {
    @Autowired
    private ModelMapper modelMapper;

    public List<EstadoDTO> findAllEstados() {
        List<EstadoDTO> estadoDTOList = new LinkedList<EstadoDTO>();
        try {
            String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
            RestTemplate httpClient = new RestTemplate();
            Object[] estados = httpClient.getForObject(url, Object[].class);
            for (Object o : estados) {
                estadoDTOList.add(modelMapper.map(o, EstadoDTO.class));
            }
            return estadoDTOList;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return estadoDTOList;
        }
    }
    public List<MunicipioDTO> findMunicipiosByUF(Integer id){
        List<MunicipioDTO> municipioDTOList = new LinkedList<MunicipioDTO>();
        try{
            String url = String.format("https://servicodados.ibge.gov.br/api/v1/localidades/estados/%d/municipios", id);
            RestTemplate httpClient = new RestTemplate();
            Object[] municipios = httpClient.getForObject(url, Object[].class);
            for (Object m : municipios) {
                municipioDTOList.add(modelMapper.map(m, MunicipioDTO.class));
            }
            return municipioDTOList;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return municipioDTOList;
        }
    }

}
