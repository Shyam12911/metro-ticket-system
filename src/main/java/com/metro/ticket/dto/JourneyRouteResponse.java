package com.metro.ticket.dto;

import com.metro.ticket.model.Journey;
import java.util.List;

public class JourneyRouteResponse {

    private List<Journey> segments;
    private int totalFare;
    private int totalDistanceKm;

    public JourneyRouteResponse(
            List<Journey> segments,
            int totalFare,
            int totalDistanceKm) {
        this.segments = segments;
        this.totalFare = totalFare;
        this.totalDistanceKm = totalDistanceKm;
    }

    public List<Journey> getSegments() {
        return segments;
    }

    public int getTotalFare() {
        return totalFare;
    }

    public int getTotalDistanceKm() {
        return totalDistanceKm;
    }
}
