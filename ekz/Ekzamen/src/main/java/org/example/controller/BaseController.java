package org.example.controller;

import org.example.mapper.Mapper;
import org.example.model.IId;
import org.example.service.Service;

public abstract class BaseController<E extends IId<Id>, DTO extends IId<Id>, Id> {
    protected Service<E, Id> service;
    protected Mapper<E, DTO> mapper;

    public BaseController() {
    }

    public BaseController(Service<E, Id> service, Mapper<E, DTO> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public DTO create(DTO dto) throws Exception{
        E entity = mapper.toEntity(dto);
        service.create(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public DTO get(Id id) throws Exception {
        return mapper.toDTO(service.get(id));
    }

    public void update(DTO dto) throws Exception {
        service.update(mapper.toEntity(dto));
    }

    public boolean delete(Id id) throws Exception {
        return service.delete(id);
    }
}
