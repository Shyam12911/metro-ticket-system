package com.metro.ticket.controller;

import com.metro.ticket.model.Journey;
import com.metro.ticket.service.JourneyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journeys")
public class JourneyController {

    private final JourneyService journeyService;

    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @GetMapping
    public List<Journey> search(
            @RequestParam String origin,
            @RequestParam String destination) {
        return journeyService.search(origin, destination);
    }
}
