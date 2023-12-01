package org.example.dao;

import org.example.model.Airline;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class AirlineDBDaoTest {

        private AirlineDBDao airlineDBDao;

        @BeforeEach
        public void setUp() throws Exception {
            var manager = DAOManager.getInstance();
            airlineDBDao = (AirlineDBDao) manager.getDAO(DAOManager.Table.AIRLINE);

        }

        @Test
        public void testCreate() throws Exception {
//            Airline airline = new Airline();
//            airlineDBDao.create(airline);
//            assertNotNull(airlineDBDao.read(airline.getId()));
        }

        @AfterAll
        public static void tearDown() throws Exception {
            var manager = DAOManager.getInstance();
            manager.close();
        }
}
