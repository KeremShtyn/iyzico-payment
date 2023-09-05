package com.iyzico.challenge.mapper;

import org.mapstruct.Mapper;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface BaseDTOMapper<Entity, DTO> {

    Entity toEntity(DTO dto);

    List<DTO> toListDTO(List<Entity> entityObjects);

    DTO toDTO(Entity entity);

    List<Entity> toEntityList(List<DTO> dtos);

}
