package org.example.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class Mapper<E, DTO> {
    abstract DTO toDTO(E entity);
    abstract E toEntity(DTO dto);
    List<DTO> toDTOList(List<E> entities) {
        List<DTO> dtos = new ArrayList<>();
        for (E entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }
    List<E> toEntityList(List<DTO> dtos) {
        List<E> entities = new ArrayList<>();
        for (DTO dto : dtos) {
            entities.add(toEntity(dto));
        }
        return entities;
    }
}
