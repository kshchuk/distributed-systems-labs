package org.example.service;

import org.example.dao.FlightDao;
import org.example.model.Flight;

import java.util.List;
import java.util.UUID;

public interface FlightService {
    void create(Flight flight);
    List<Flight> findAll();
    List<Flight> findAllByAirline(UUID airlineId);
    List<Flight> findAllByOrigin(String origin);
    List<Flight> findAllByDestination(String destinationn);
    boolean delete(UUID id);
    void update(Flight flight);
    boolean containsId(UUID id);
    void changeSource(FlightDao dao);
    Flight get(UUID id);
}
