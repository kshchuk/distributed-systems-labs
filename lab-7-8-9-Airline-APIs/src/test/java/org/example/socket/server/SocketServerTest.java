package org.example.socket.server;

import org.example.controller.AirlineController;
import org.example.controller.FlightController;
import org.example.dao.AirlineDao;
import org.example.dao.FlightDao;
import org.example.dao.db.DAOManager;
import org.example.mapper.AirlineMapper;
import org.example.mapper.FlightMapper;
import org.example.service.AirlineService;
import org.example.service.AirlineServiceImpl;
import org.example.service.FlightService;
import org.example.service.FlightServiceImpl;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.dto.AirlineDto;
import org.example.dto.FlightDto;
import org.example.dto.Request;
import org.example.dto.Response;
import org.example.socket.client.Client;

import java.sql.SQLException;

public class SocketServerTest {
    SocketServer socketServer;
    Client client;

    @BeforeEach
public void setUp() throws SQLException {
        var daoManager = DAOManager.getInstance();

        FlightDao flightDao = (FlightDao) daoManager.getDAO(DAOManager.Table.FLIGHT);
        AirlineDao airlineDao = (AirlineDao) daoManager.getDAO(DAOManager.Table.AIRLINE);

        FlightService flightService = new FlightServiceImpl(flightDao, airlineDao);
        AirlineService airlineService = new AirlineServiceImpl(airlineDao);

        FlightMapper flightMapper = new FlightMapper();
        AirlineMapper airlineMapper = new AirlineMapper();

        FlightController flightController = new FlightController(flightService, flightMapper, airlineService);
        AirlineController airlineController = new AirlineController(airlineService, airlineMapper);

        socketServer = new SocketServer(8080, flightController, airlineController);
        socketServer.start();

        client = new Client();
        client.Connect("localhost", 8080);
    }

    @Test
public void testGetAllAirlines() throws Exception {
        Request request = new Request(Request.RequestMethod.GET, "/airline", null);
        client.sOutput.writeObject(request);
        Response response = (Response) client.sInput.readObject();
        assertEquals(Response.ResponseStatus.SUCCESS, response.getStatus());
        assertEquals(0, ((AirlineDto[]) response.getBody()).length);
    }

    @AfterEach
public void tearDown() {
        client.Stop();
        socketServer.Stop();
    }
}
