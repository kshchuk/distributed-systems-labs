package org.example.mapper;

import org.example.dto.FlightDto;
import org.example.model.Flight;

public class FlightMapper implements Mapper<Flight, FlightDto> {
    @Override
    public FlightDto toDTO(Flight entity) {
        FlightDto dto = new FlightDto();
        dto.setOrigin(entity.getOrigin());
        dto.setDestination(entity.getDestination());
        dto.setDepartureTime(entity.getDepartureTime());
        dto.setArrivalTime(entity.getArrivalTime());
        return dto;
    }

    @Override
    public Flight toEntity(FlightDto dto) {
        Flight entity = new Flight();
        entity.setOrigin(dto.getOrigin());
        entity.setDestination(dto.getDestination());
        entity.setDepartureTime(dto.getDepartureTime());
        entity.setArrivalTime(dto.getArrivalTime());
        return entity;
    }
}
