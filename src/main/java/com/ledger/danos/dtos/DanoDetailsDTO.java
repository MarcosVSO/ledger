package com.ledger.danos.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DanoDetailsDTO {
    private Long id;
    private String descricao;
    private List<String> fotos = new ArrayList<>();
    private String latitude;
    private String longitude;
}
