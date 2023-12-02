package org.example.service;

import org.example.dao.AirlineDao;
import org.example.model.Airline;

import java.util.List;
import java.util.UUID;

public interface AirlineService {
    void create(Airline airline);
    List<Airline> findAll();
    List<Airline> findAllByCountry(String country);
    boolean delete(UUID id);
    void update(Airline airline);
    boolean containsId(UUID id);
    void changeSource(AirlineDao dao);
    Airline get(UUID id);
}
