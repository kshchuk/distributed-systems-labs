package org.example.service;

import org.example.dao.AirlineDao;
import org.example.dao.FlightDao;
import org.example.model.Flight;

import java.util.List;
import java.util.UUID;

public class FlightServiceImpl implements FlightService {
    private FlightDao dao;
    private AirlineDao airlineDao;

    public FlightServiceImpl(FlightDao dao, AirlineDao airlineDao) {
        this.dao = dao;
        this.airlineDao = airlineDao;
    }

    @Override
    public void create(Flight flight) {

    }

    @Override
    public List<Flight> findAll() {
        return null;
    }

    @Override
    public List<Flight> findAllByAirline(UUID airlineId) {
        return null;
    }

    @Override
    public List<Flight> findAllByOrigin(String origin) {
        return null;
    }

    @Override
    public List<Flight> findAllByDestination(String destinationn) {
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public void update(Flight flight) {

    }

    @Override
    public boolean containsId(UUID id) {
        return false;
    }

    @Override
    public void changeSource(FlightDao dao) {

    }

    @Override
    public Flight get(UUID id) {
        return null;
    }
}
