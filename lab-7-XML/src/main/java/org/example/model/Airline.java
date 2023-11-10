package org.example.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.UUID;

@Data
public class Airline {
    private UUID airline_id;
    private String name;
    private String code;
    private String country;
    private ArrayList<Flight> flights;
}
