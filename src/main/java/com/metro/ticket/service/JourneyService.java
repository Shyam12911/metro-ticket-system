package com.metro.ticket.service;

import com.metro.ticket.model.Journey;
import com.metro.ticket.repository.JourneyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JourneyService {
    private final JourneyRepository repo;

    public JourneyService(JourneyRepository repo) {
        this.repo = repo;
    }

    public List<Journey> search(String origin, String dest) {
        return repo.findByOriginIgnoreCaseAndDestinationIgnoreCase(origin, dest);
    }

    public Journey createSample(String origin, String dest, LocalDateTime time, double fare) {
        Journey j = new Journey();
        j.setOrigin(origin);
        j.setDestination(dest);
        j.setDepartureTime(time);
        j.setFare(fare);
        return repo.save(j);
    }
}
