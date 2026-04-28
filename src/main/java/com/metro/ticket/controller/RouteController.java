package com.metro.ticket.controller;

import com.metro.ticket.model.Journey;
import com.metro.ticket.repository.JourneyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final JourneyRepository repo;

    public RouteController(JourneyRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Journey> getAllRoutes() {
        return repo.findAll();
    }
}
