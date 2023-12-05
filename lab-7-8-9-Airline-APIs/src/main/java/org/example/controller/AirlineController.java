package org.example.controller;

import org.example.dto.AirlineDto;
import org.example.dto.FlightDto;
import org.example.mapper.AirlineMapper;
import org.example.mapper.Mapper;
import org.example.model.Airline;
import org.example.service.AirlineService;
import org.example.service.Service;

import java.util.List;
import java.util.UUID;

public class AirlineController extends BaseController<Airline, AirlineDto, UUID>{
    public AirlineController(AirlineService service, AirlineMapper mapper) {
        super(service, mapper);
    }

    public List<AirlineDto> findAll() {
        return ((AirlineService)service).findAll().stream().map(mapper::toDTO).toList();
    }
}
