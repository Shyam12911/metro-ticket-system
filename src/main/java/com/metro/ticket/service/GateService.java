package com.metro.ticket.service;

import com.metro.ticket.model.GateScan;
import com.metro.ticket.model.Ticket;
import com.metro.ticket.repository.GateScanRepository;
import com.metro.ticket.repository.TicketRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class GateService {

    private final TicketRepository ticketRepo;
    private final GateScanRepository scanRepo;

    public GateService(
            TicketRepository ticketRepo,
            GateScanRepository scanRepo) {
        this.ticketRepo = ticketRepo;
        this.scanRepo = scanRepo;
    }

    public void scan(
            String qrToken,
            String type,
            String station,
            int platform,
            String equipment) {

        Ticket ticket = ticketRepo.findByQrToken(qrToken)
                .orElseThrow(() -> new RuntimeException("Invalid QR"));

        if (!"ACTIVE".equals(ticket.getStatus())) {
            throw new RuntimeException("Ticket invalid");
        }

        if (ticket.getValidUpto().isBefore(LocalDateTime.now())) {
            ticket.setStatus("EXPIRED");
            ticketRepo.save(ticket);
            throw new RuntimeException("Ticket expired");
        }

        if ("EXIT".equals(type)) {
            ticket.setStatus("USED");
            ticketRepo.save(ticket);
        }

        GateScan scan = new GateScan();
        scan.setTicket(ticket);
        scan.setScanType(type);
        scan.setStation(station);
        scan.setPlatformNo(platform);
        scan.setEquipmentId(equipment);
        scan.setScannedAt(LocalDateTime.now());

        scanRepo.save(scan);
    }
}
