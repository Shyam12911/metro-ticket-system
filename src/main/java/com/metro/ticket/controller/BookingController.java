package com.metro.ticket.controller;

import org.springframework.web.bind.annotation.*;

import com.metro.ticket.dto.BookingRequest;
import com.metro.ticket.model.Ticket;
import com.metro.ticket.service.TicketService;

import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final TicketService ticketService;

    public BookingController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/initiate")
    public Ticket initiate(@RequestBody BookingRequest request) {

        return ticketService.initiateBooking(
                Optional.ofNullable(request.getUserId()),
                Optional.ofNullable(request.getJourneyId()));
    }
}
