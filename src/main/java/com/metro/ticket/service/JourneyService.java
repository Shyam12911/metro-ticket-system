package com.metro.ticket.service;

import com.metro.ticket.dto.RouteResultDTO;
import com.metro.ticket.model.Journey;
import com.metro.ticket.repository.JourneyRepository;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;

import java.util.List;

@Service
public class JourneyService {

    private final JourneyRepository repo;

    public JourneyService(JourneyRepository repo) {
        this.repo = repo;
    }

    // EXISTING logic — unchanged
    @NonNull
    public RouteResultDTO searchRoute(
            @NonNull String source,
            @NonNull String destination) {

        List<Journey> journeys = repo
                .findBySourceIgnoreCaseOrderByDepartureTimeAsc(source)
                .stream()
                .filter(j -> j.getDestination().equalsIgnoreCase(destination))
                .toList();

        double fare = journeys.stream()
                .mapToDouble(Journey::getFare)
                .sum();

        int distance = journeys.stream()
                .mapToInt(Journey::getDistanceKm)
                .sum();

        return new RouteResultDTO(journeys, fare, distance);
    }

    // REQUIRED by JourneyController
    @NonNull
    public Journey save(@NonNull Journey journey) {
        return repo.save(journey);
    }
}