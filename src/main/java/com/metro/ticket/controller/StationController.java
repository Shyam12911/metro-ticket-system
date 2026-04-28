package com.metro.ticket.controller;

import com.metro.ticket.model.Station;
import com.metro.ticket.repository.StationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    private final StationRepository stationRepo;

    public StationController(StationRepository stationRepo) {
        this.stationRepo = stationRepo;
    }

    @GetMapping
    public List<Station> allStations() {
        return stationRepo.findAll();
    }
}