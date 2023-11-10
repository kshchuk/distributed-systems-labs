package org.example.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Flight {
    private UUID id;
    private String origin;
    private String destination;
    private String airline;
    private String flightNumber;
    private Long departureTime;
    private Long arrivalTime;
}
