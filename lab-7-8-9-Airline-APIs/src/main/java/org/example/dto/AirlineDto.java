package org.example.dto;

import lombok.Data;

import java.util.ArrayList;


@Data
public class AirlineDto implements java.io.Serializable {
    private String name;
    private String code;
    private String country;
    private ArrayList<FlightDto> flights;
}
