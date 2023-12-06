package org.example.dto;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Data
public class FlightDto implements java.io.Serializable {
    @Nullable
    private UUID flightId = null;
    private String origin;
    private String destination;
    private String flightNumber;
    private UUID airlineId;
    private Long departureTime;
    private Long arrivalTime;

    @Override
    public String toString() {
        return "FlightDto{" +
                "flightId=" + (flightId != null ? flightId.toString()  : " ") +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", airlineId=" + airlineId +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
