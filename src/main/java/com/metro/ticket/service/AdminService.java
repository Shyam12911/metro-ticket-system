package com.metro.ticket.service;

import com.metro.ticket.dto.AdminStatsDTO;
import com.metro.ticket.model.Ticket;
import com.metro.ticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class AdminService {

    private final TicketRepository ticketRepo;

    public AdminService(TicketRepository ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public AdminStatsDTO getStats() {
        List<Ticket> all = ticketRepo.findAll();

        long total = all.size();

        BigDecimal revenue = all.stream()
                .filter(t -> t.getIssuedAt() != null)
                .filter(t -> t.getIssuedAt().toLocalDate().equals(LocalDate.now()))
                .map(Ticket::getFare)
                .filter(f -> f != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new AdminStatsDTO(total, revenue);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    public Ticket invalidateTicket(Long id) {
        Ticket t = ticketRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        t.setStatus("EXPIRED");
        return ticketRepo.save(t);
    }

    public String exportCsv() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID,FROM,TO,STATUS,FARE,ISSUED_AT\n");

        for (Ticket t : ticketRepo.findAll()) {
            sb.append(safe(t.getId())).append(",")
                    .append(safe(t.getFromStation())).append(",")
                    .append(safe(t.getToStation())).append(",")
                    .append(safe(t.getStatus())).append(",")
                    .append(safe(t.getFare())).append(",")
                    .append(safe(t.getIssuedAt()))
                    .append("\n");
        }
        return sb.toString();
    }

    private String safe(Object o) {
        return o == null ? "" : o.toString();
    }
}