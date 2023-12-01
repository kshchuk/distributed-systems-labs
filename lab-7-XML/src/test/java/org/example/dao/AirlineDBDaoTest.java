package org.example.dao;

import org.example.model.Airline;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class AirlineDBDaoTest {

        private AirlineDBDao airlineDBDao;
        private int dataBaseSize;

        @BeforeEach
        public void setUp() throws Exception {
            var manager = DAOManager.getInstance();
            airlineDBDao = (AirlineDBDao) manager.getDAO(DAOManager.Table.AIRLINE);
            dataBaseSize = airlineDBDao.findAll().size();
        }

        @Test
        public void testCreate() throws Exception {
            var airline = new Airline();
            airline.setName("Test Airline");
            airlineDBDao.create(airline);
            var airlines = airlineDBDao.findAll();
            assertEquals(dataBaseSize + 1, airlines.size());
            assertEquals("Test Airline", airlines.get(0).getName());
            dataBaseSize++;
        }

        @Test
        public void testUpdate() throws Exception {
            var airline = new Airline();
            airline.setName("Test Airline");
            airlineDBDao.create(airline);
            var airlines = airlineDBDao.findAll();
            assertEquals(dataBaseSize + 1, airlines.size());
            dataBaseSize++;
            assertEquals("Test Airline", airlines.getLast().getName());
            airline.setName("Updated Airline");
            airlineDBDao.update(airline);
            airlines = airlineDBDao.findAll();
            assertEquals(dataBaseSize, airlines.size());
            assertEquals("Updated Airline", airlines.getLast().getName());
        }

        @Test
        public void testDelete() throws Exception {
            var airline = new Airline();
            airline.setName("Test Airline");
            airlineDBDao.create(airline);
            var airlines = airlineDBDao.findAll();
            assertEquals(dataBaseSize + 1, airlines.size());
            assertEquals("Test Airline", airlines.get(0).getName());
            airlineDBDao.delete(airline.getId());
            airlines = airlineDBDao.findAll();
            assertEquals(dataBaseSize, airlines.size());
        }

        @Test
        public void testFindAll() throws Exception {
            var airline = new Airline();
            airline.setName("Test Airline");
            airlineDBDao.create(airline);
            var airlines = airlineDBDao.findAll();
            assertEquals(dataBaseSize + 1, airlines.size());
            dataBaseSize++;
            assertEquals("Test Airline", airlines.get(0).getName());
        }

        @Test
        public void testRead() throws Exception {
            var airline = new Airline();
            airline.setName("Test Airline");
            airlineDBDao.create(airline);
            var airlines = airlineDBDao.findAll();
            assertEquals(dataBaseSize + 1, airlines.size());
            dataBaseSize++;
            assertEquals("Test Airline", airlines.get(0).getName());
            var airline2 = airlineDBDao.read(airline.getId());
            assertEquals("Test Airline", airline2.getName());
        }

        @AfterAll
        public static void tearDown() throws Exception {
            var manager = DAOManager.getInstance();
            manager.close();
        }
}
