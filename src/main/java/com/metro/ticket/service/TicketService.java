package com.metro.ticket.service;

import java.util.Objects;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metro.ticket.model.BookingStatus;
import com.metro.ticket.model.Journey;
import com.metro.ticket.model.Ticket;
import com.metro.ticket.repository.JourneyRepository;
import com.metro.ticket.repository.TicketRepository;
import com.metro.ticket.repository.UserRepository;

@Service
public class TicketService {

    private final TicketRepository ticketRepo;
    private final JourneyRepository journeyRepo;
    private final UserRepository userRepo;

    public TicketService(TicketRepository t,
            JourneyRepository j,
            UserRepository u) {
        this.ticketRepo = t;
        this.journeyRepo = j;
        this.userRepo = u;
    }

    @Transactional
    public Ticket initiateBooking(Optional<Long> userIdOpt,
            Optional<Long> journeyIdOpt) {

        // ---- EXPLICIT NULL CONTRACT ----
        Long userId = userIdOpt.orElseThrow(() -> new IllegalArgumentException("userId is required"));

        Long journeyId = journeyIdOpt.orElseThrow(() -> new IllegalArgumentException("journeyId is required"));

        // ---- LOAD JOURNEY (NON-NULL GUARANTEE) ----
        Journey journey = journeyRepo
                .findById(Objects.requireNonNull(journeyId))
                .orElseThrow(() -> new RuntimeException("Journey not found"));

        if (journey.getAvailableSeats() <= 0) {
            throw new RuntimeException("No seats available");
        }

        // ---- RESERVE CAPACITY ----
        journey.setAvailableSeats(journey.getAvailableSeats() - 1);

        // ---- CREATE TICKET (PAYMENT PENDING) ----
        Ticket ticket = new Ticket();
        ticket.setUser(
                userRepo.findById(Objects.requireNonNull(userId))
                        .orElseThrow(() -> new RuntimeException("User not found")));
        ticket.setJourney(journey);
        ticket.setStatus(BookingStatus.PAYMENT_PENDING);
        ticket.setCreatedAt(LocalDateTime.now());

        return ticketRepo.save(ticket);
    }
}
