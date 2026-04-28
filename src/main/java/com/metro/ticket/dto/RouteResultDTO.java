package com.metro.ticket.dto;

import com.metro.ticket.model.Journey;
import java.util.List;

public class RouteResultDTO {

    private List<Journey> journeys;
    private double fare;
    private int stations;

    public RouteResultDTO(List<Journey> journeys, double fare, int stations) {
        this.journeys = journeys;
        this.fare = fare;
        this.stations = stations;
    }

    public List<Journey> getJourneys() {
        return journeys;
    }

    public double getFare() {
        return fare;
    }

    public int getStations() {
        return stations;
    }
}