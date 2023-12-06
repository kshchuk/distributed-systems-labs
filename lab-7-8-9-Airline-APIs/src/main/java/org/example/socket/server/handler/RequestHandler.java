package org.example.socket.server.handler;

import org.example.controller.AirlineController;
import org.example.controller.FlightController;
import org.example.dto.Request;
import org.example.dto.Response;

import java.util.UUID;

public class RequestHandler {
    private final AirlineController airlineController;
    private final FlightController flightController;

    public RequestHandler(AirlineController airlineController, FlightController flightController) {
        this.airlineController = airlineController;
        this.flightController = flightController;
    }

    public Response handle(Request request) throws Exception {
        switch (request.getMethod()) {
            case GET:
                return switch (request.getPath()) {
                    case "/airline" -> new Response(Response.ResponseStatus.SUCCESS, airlineController.findAll());
                    case "/airline/{airlineId}/flights" -> {
                        var airlineId = UUID.fromString(request.getPath().split("/")[2]);
                        yield new Response(Response.ResponseStatus.SUCCESS, flightController.findAllByAirline(airlineId));
                    }
                    case "/airline/name/{airlineName}/flights" -> {
                        var airlineName = request.getPath().split("/")[4];
                        yield new Response(Response.ResponseStatus.SUCCESS, flightController.findAllByAirline(airlineName));
                    }
                    case "/flight/{flightId}" -> {
                        var flightId = UUID.fromString(request.getPath().split("/")[2]);
                        yield new Response(Response.ResponseStatus.SUCCESS, flightController.get(flightId));
                    }
                    case "/airline/{airlineId}" -> {
                        var airlineId2 = UUID.fromString(request.getPath().split("/")[2]);
                        yield new Response(Response.ResponseStatus.SUCCESS, airlineController.get(airlineId2));
                    }
                    default -> new Response(Response.ResponseStatus.ERROR, "Not found");
                };
            case POST:
                switch (request.getPath()) {
                    case "/airline" -> {
                        var airlineDto = (org.example.dto.AirlineDto) request.getBody();
                        this.airlineController.create(airlineDto);
                        return new Response(Response.ResponseStatus.SUCCESS, null);
                    }
                    case "/flight" -> {
                        var flightDto = (org.example.dto.FlightDto) request.getBody();
                        this.flightController.create(flightDto);
                        return new Response(Response.ResponseStatus.SUCCESS, null);
                    }
                    default -> new Response(Response.ResponseStatus.ERROR, "Not found");
                }
                break;
            case PUT:
                switch (request.getPath()) {
                    case "/airline" -> {
                        var airlineDto = (org.example.dto.AirlineDto) request.getBody();
                        this.airlineController.update(airlineDto);
                        return new Response(Response.ResponseStatus.SUCCESS, null);
                    }
                    case "/flight" -> {
                        var flightDto = (org.example.dto.FlightDto) request.getBody();
                        this.flightController.update(flightDto);
                        return new Response(Response.ResponseStatus.SUCCESS, null);
                    }
                    default -> new Response(Response.ResponseStatus.ERROR, "Not found");
                }
                break;
            case DELETE:
                switch (request.getPath()) {
                    case "/airline/{airlineId}" -> {
                        var airlineId = UUID.fromString(request.getPath().split("/")[2]);
                        this.airlineController.delete(airlineId);
                        return new Response(Response.ResponseStatus.SUCCESS, null);
                    }
                    case "/flight/{flightId}" -> {
                        var flightId = UUID.fromString(request.getPath().split("/")[2]);
                        this.flightController.delete(flightId);
                        return new Response(Response.ResponseStatus.SUCCESS, null);
                    }
                    default -> new Response(Response.ResponseStatus.ERROR, "Not found");
                }
                break;
            case PATCH:
                break;
            case HEAD:
                break;
            case OPTIONS:
                break;
            case TRACE:
                break;
            case CONNECT:
                break;
            default:
                throw new Exception("Unknown request method");

        }
        return new Response(Response.ResponseStatus.ERROR, "Not found");
    }
}
