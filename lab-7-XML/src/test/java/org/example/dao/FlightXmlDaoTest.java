package org.example.dao;

import org.example.dao.xml.FlightXmlDao;
import org.example.model.Airline;
import org.example.model.Airlines;
import org.example.model.Flight;
import org.example.xml.dom.reader.Reader;
import org.example.xml.dom.writer.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.xml.validation.Schema;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightXmlDaoTest {

    @Mock
    private Reader<Airlines> reader;

    @Mock
    private Writer<Airlines> writer;

    @Mock
    private Schema schema;

    private FlightXmlDao flightXmlDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        flightXmlDao = new FlightXmlDao("src\\test\\resources\\test_flight_dao.xml", reader, writer, schema);
    }

//    @Test
//    public void testToCollection() {
//        Airlines airlines = mock(Airlines.class);
//        Airline airline = mock(Airline.class);
//        Flight flight = mock(Flight.class);
//
//        when(airlines.getAirlines()).thenReturn(new ArrayList<>(Collections.singletonList(airline)));
//        when(airline.getFlights()).thenReturn(new ArrayList<>(Collections.singletonList(flight)));
//
//        List<Flight> result = flightXmlDao.toCollection(airlines);
//
//        assertEquals(1, result.size());
//        assertEquals(flight, result.get(0));
//    }

    @Test
    public void testCreate() throws Exception {
        Flight flight = mock(Flight.class);
        when(flightXmlDao.create(flight)).thenReturn(flight);
        assertEquals(flight, flightXmlDao.create(flight));
    }

    @Test
    public void testRead() throws Exception {
        UUID id = UUID.randomUUID();
        Flight flight = mock(Flight.class);
        when(flightXmlDao.read(id)).thenReturn(flight);
        assertEquals(Optional.of(flight), flightXmlDao.read(id));
    }

    @Test
    public void testUpdate() throws Exception {
        Flight flight = mock(Flight.class);
        flightXmlDao.update(flight);
        verify(flightXmlDao, times(1)).update(flight);
    }

    @Test
    public void testDelete() throws Exception {
        UUID id = UUID.randomUUID();
        flightXmlDao.delete(id);
        verify(flightXmlDao, times(1)).delete(id);
    }

    @Test
    public void testFindAll() throws Exception {
        Flight flight = mock(Flight.class);
        when(flightXmlDao.findAll()).thenReturn(Collections.singletonList(flight));
        assertEquals(Collections.singletonList(flight), flightXmlDao.findAll());
    }
}

