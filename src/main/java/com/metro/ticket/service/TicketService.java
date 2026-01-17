package com.metro.ticket.service;

import com.metro.ticket.model.Journey;
import com.metro.ticket.model.Ticket;
import com.metro.ticket.model.User;
import com.metro.ticket.repository.JourneyRepository;
import com.metro.ticket.repository.TicketRepository;
import com.metro.ticket.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepo;
    private final JourneyRepository journeyRepo;
    private final UserRepository userRepo;

    public TicketService(TicketRepository ticketRepo, JourneyRepository journeyRepo, UserRepository userRepo) {
        this.ticketRepo = ticketRepo;
        this.journeyRepo = journeyRepo;
        this.userRepo = userRepo;
    }

    public Ticket book(Long journeyId, String userName, String userEmail, int seats) {

        if (journeyId == null) {
            throw new IllegalArgumentException("Journey id cannot be null");
        }

        Optional<Journey> oj = journeyRepo.findById(journeyId);

        if (oj.isEmpty()) {
            throw new IllegalArgumentException("Journey not found");
        }

        Journey j = oj.get();

        User user = new User();
        user.setName(userName);
        user.setEmail(userEmail);
        user = userRepo.save(user);

        Ticket t = new Ticket();
        t.setJourney(j);
        t.setUser(user);
        t.setSeatCount(seats);
        t.setTotalPrice(j.getFare() * seats);
        return ticketRepo.save(t);
    }
}
