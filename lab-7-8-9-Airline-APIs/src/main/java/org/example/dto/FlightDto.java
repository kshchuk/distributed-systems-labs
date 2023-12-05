package org.example.dto;

import lombok.Data;

@Data
public class FlightDto implements java.io.Serializable {
    private String origin;
    private String destination;
    private String flightNumber;
    private Long departureTime;
    private Long arrivalTime;
}
