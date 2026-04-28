package com.metro.ticket.repository;

import com.metro.ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByQrToken(String qrToken);

    // ✅ NEW: For ticket number generation
    long countByIssuedAtBetween(LocalDateTime start, LocalDateTime end);
}