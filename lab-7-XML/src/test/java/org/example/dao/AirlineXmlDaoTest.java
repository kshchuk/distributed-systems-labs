package org.example.dao;

import org.example.dao.xml.AirlineXmlDao;
import org.example.model.Airline;
import org.example.model.Airlines;
import org.example.xml.dom.reader.Reader;
import org.example.xml.dom.writer.Writer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.xml.validation.Schema;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AirlineXmlDaoTest {

    @Mock
    private Reader<Airlines> reader;

    @Mock
    private Writer<Airlines> writer;

    @Mock
    private Schema schema;

    private AirlineXmlDao airlineXmlDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        airlineXmlDao = new AirlineXmlDao("src\\test\\resources\\test_airline_dao.xml", reader, writer, schema);
    }

    @Test
    public void testCreate() throws Exception {
        Airline airline = mock(Airline.class);
        doNothing().when(writer).write(anyString(), any(Airlines.class));
        assertEquals(airline, airlineXmlDao.create(airline));
    }

    @Test
    public void testRead() throws Exception {
        UUID id = UUID.randomUUID();
        Airline airline = mock(Airline.class);
        when(airlineXmlDao.read(id)).thenReturn(airline);
        assertEquals(airline, airlineXmlDao.read(id));
    }

    @Test
    public void testUpdate() throws Exception {
        Airline airline = mock(Airline.class);
        airlineXmlDao.update(airline);
        verify(airlineXmlDao, times(1)).update(airline);
    }

    @Test
    public void testDelete() throws Exception {
        UUID id = UUID.randomUUID();
        airlineXmlDao.delete(id);
        verify(airlineXmlDao, times(1)).delete(id);
    }

    @Test
    public void testFindAll() throws Exception {
        Airline airline = mock(Airline.class);
        when(airlineXmlDao.findAll()).thenReturn(Collections.singletonList(airline));
        assertEquals(Collections.singletonList(airline), airlineXmlDao.findAll());
    }

//    @Test
//    public void testToCollection() {
//        Airlines airlines = mock(Airlines.class);
//        Airline airline = mock(Airline.class);
//
//        when(airlines.getAirlines()).thenReturn(new ArrayList<>(Collections.singletonList(airline)));
//
//        List<Airline> result = airlineXmlDao.toCollection(airlines);
//
//        Assertions.assertEquals(1, result.size());
//        Assertions.assertEquals(airline, result.get(0));
//    }
}
