package org.example.controller;

import org.example.dto.FlightDto;
import org.example.mapper.FlightMapper;
import org.example.model.Flight;
import org.example.service.AirlineService;
import org.example.service.FlightService;

import java.util.*;

public class FlightController extends BaseController<Flight, FlightDto, UUID> {
    private final AirlineService airlineService;
    public FlightController(FlightService service, FlightMapper mapper, AirlineService airlineService) {
        super(service, mapper);
        this.airlineService = airlineService;
    }
    public List<FlightDto> findAllByAirline(UUID airlineId) {
        return ((FlightService)service).findAllByAirline(airlineId).stream().map(mapper::toDTO).toList();
    }

    public List<FlightDto> findAllByAirline(String airlineName) {
        return ((FlightService)service).findAllByAirline(airlineService.findByName(airlineName).get(0).getId()).stream().map(mapper::toDTO).toList();
    }
}
