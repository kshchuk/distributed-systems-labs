package org.example.socket.server;

import org.example.controller.AirlineController;
import org.example.controller.FlightController;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

//This is a TCP protocol connection based server.
public class SocketServer {
    public FlightController flightController;
    public AirlineController airlineController;

    public ServerSocket socket;
    public int port;
    public ListenConnectionRequestThread listenConnectionRequestThread;
    public ClientRemovingControlThread removingControlThread;
    public static ArrayList<SClient> clients;

    public SocketServer(int port, FlightController flightController, AirlineController airlineController) {
        try {
            this.port = port;
            this.flightController = flightController;
            this.airlineController = airlineController;
            this.socket = new ServerSocket(this.port);
            this.listenConnectionRequestThread = new ListenConnectionRequestThread(this);
            removingControlThread = new ClientRemovingControlThread(this);
            clients = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("There is an error occurred when opening the server on port:" + this.port);

        }
    }

    // Starts the acceptance
    public void ListenClientConnectionRequests() {
        this.listenConnectionRequestThread.start();
    }
}
