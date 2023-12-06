package org.example.dto;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.UUID;


@Data
public class AirlineDto implements java.io.Serializable {
    @Nullable
    private UUID AirlineId = null;
    private String name;
    private String code;
    private String country;
    private ArrayList<FlightDto> flights;

    @Override
    public String toString() {
        return "AirlineDto{" +
                "AirlineId=" + (AirlineId != null ? AirlineId.toString()  : " ") +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", country='" + country + '\'' +
                ", flights=" + (flights != null ? flights.size() : 0) +
                '}';
    }
}
