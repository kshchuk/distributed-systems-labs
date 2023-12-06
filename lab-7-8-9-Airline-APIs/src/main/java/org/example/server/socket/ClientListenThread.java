package org.example.server.socket;

import org.example.controller.AirlineController;
import org.example.controller.FlightController;
import org.example.dto.Request;
import org.example.server.handler.RequestHandler;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


// The purpose of this thread is listening data incoming to the sclients input stream. After a data come this thread determines what will
// going to do with this data and then recontinue to the listening the input stream. This listening never ends until the sclient connection
// is lost...
public class ClientListenThread extends Thread {
    FlightController flightController;
    AirlineController airlineController;
    RequestHandler requestHandler;
    SClient client;

    public ClientListenThread(SClient client, FlightController flightController, AirlineController airlineController) {
        this.client = client;
        this.flightController = flightController;
        this.airlineController = airlineController;
        this.requestHandler = new RequestHandler(this.airlineController, this.flightController);
    }

    @Override
    public void run() {
        while (!this.client.socket.isClosed()) {
            try {
                Request request = (Request) (this.client.cInput.readObject());
                System.out.println("Request received from client: " + request);
                var response = this.requestHandler.handle(request);
                this.client.Send(response);
            } catch (Exception e) {
                Logger.getLogger(ClientListenThread.class.getName()).log(Level.SEVERE, null, e);
                this.client.Send(new org.example.dto.Response(org.example.dto.Response.ResponseStatus.ERROR, e.getMessage()));
            }
        }
    }
}
