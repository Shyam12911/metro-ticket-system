package com.metro.ticket.controller;

import com.metro.ticket.dto.RouteResultDTO;
import com.metro.ticket.model.Journey;
import com.metro.ticket.service.JourneyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/journeys")
public class JourneyController {

    private final JourneyService journeyService;

    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @GetMapping("/route")
    public RouteResultDTO search(
            @RequestParam String source,
            @RequestParam String destination) {

        if (source == null || destination == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Source and destination required");
        }

        return journeyService.searchRoute(source, destination);
    }

    @PostMapping
    public Journey create(@RequestBody Journey journey) {
        if (journey == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Journey required");
        }
        return journeyService.save(journey);
    }
}