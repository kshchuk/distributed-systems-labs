package org.example.controller;

import org.example.mapper.Mapper;
import org.example.service.Service;

public abstract class BaseController<E, DTO, Id> {
    protected final Service<E, Id> service;
    protected final Mapper<E, DTO> mapper;

    public BaseController(Service<E, Id> service, Mapper<E, DTO> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public void create(DTO dto) {
        service.create(mapper.toEntity(dto));
    }

    public DTO get(Id id) {
        return mapper.toDTO(service.get(id));
    }

    public void update(DTO dto) {
        service.update(mapper.toEntity(dto));
    }

    public boolean delete(Id id) {
        return service.delete(id);
    }
}
