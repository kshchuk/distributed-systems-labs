package org.example.mapper;

public interface Mapper<E, DTO> {
    DTO toDTO(E entity);
    E toEntity(DTO dto);
}
