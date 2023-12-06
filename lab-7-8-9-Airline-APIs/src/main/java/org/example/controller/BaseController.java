package org.example.controller;

import org.example.mapper.Mapper;
import org.example.service.Service;

import java.util.NoSuchElementException;

public abstract class BaseController<E, DTO, Id> {
    protected final Service<E, Id> service;
    protected final Mapper<E, DTO> mapper;

    public BaseController(Service<E, Id> service, Mapper<E, DTO> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public void create(DTO dto) throws Exception{
        service.create(mapper.toEntity(dto));
    }

    public DTO get(Id id) throws NoSuchElementException {
        return mapper.toDTO(service.get(id));
    }

    public void update(DTO dto) throws NoSuchElementException {
        service.update(mapper.toEntity(dto));
    }

    public boolean delete(Id id) throws NoSuchElementException {
        return service.delete(id);
    }
}
