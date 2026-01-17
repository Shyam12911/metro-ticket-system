package com.metro.ticket.controller;

import com.metro.ticket.dto.BookingRequest;
import com.metro.ticket.model.Ticket;
import com.metro.ticket.service.TicketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final TicketService ticketService;

    public BookingController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public Ticket book(@RequestBody BookingRequest request) {
        return ticketService.book(
                request.getJourneyId(),
                request.getName(),
                request.getEmail(),
                request.getSeats());
    }
}
