package com.ledger.danos.dtos.mappers;

import com.ledger.danos.entities.Foto;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@Mapper
public interface FotoMapper {
    @SneakyThrows
    default Foto multipartFileToFoto(MultipartFile multipartFile) {
        if (multipartFile == null) {
            return null;
        }

        Foto foto = new Foto();
        var fileNameParts = StringUtils.cleanPath(multipartFile.getOriginalFilename()).split("\\.");
        var extension = fileNameParts[fileNameParts.length - 1];
        UUID uuid = UUID.randomUUID();

        foto.setName(uuid + "." + extension);
        foto.setContentType(multipartFile.getContentType());
        foto.setSize(String.valueOf(multipartFile.getSize()));
        foto.setData(multipartFile.getBytes());

        return foto;
    }

    default String fotoToPath(Foto foto) {
        if (foto == null) {
            return null;
        }

        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .pathSegment("danos")
                .pathSegment(foto.getDano().getId().toString())
                .pathSegment("fotos")
                .pathSegment(foto.getId().toString())
                .toUriString();
    }
}
