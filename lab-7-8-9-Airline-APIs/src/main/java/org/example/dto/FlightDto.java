package org.example.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class FlightDto implements java.io.Serializable {
    private String origin;
    private String destination;
    private String flightNumber;
    private UUID airlineId;
    private Long departureTime;
    private Long arrivalTime;
}
