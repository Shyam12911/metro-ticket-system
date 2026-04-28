package com.metro.ticket.service;

import com.metro.ticket.model.Station;
import com.metro.ticket.model.Ticket;
import com.metro.ticket.repository.StationRepository;
import com.metro.ticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TicketService {

    private final TicketRepository ticketRepo;
    private final StationRepository stationRepo;
    private final QRCodeService qrService;

    public TicketService(
            TicketRepository ticketRepo,
            StationRepository stationRepo,
            QRCodeService qrService) {
        this.ticketRepo = ticketRepo;
        this.stationRepo = stationRepo;
        this.qrService = qrService;
    }

    /*
     * =====================
     * ISSUE TICKET
     * =====================
     */
    public Ticket issueRouteTicket(String fromStation, String toStation) {

        Station from = stationRepo.findByNameIgnoreCase(fromStation.trim())
                .orElseThrow(() -> new RuntimeException("Invalid FROM station: " + fromStation));

        Station to = stationRepo.findByNameIgnoreCase(toStation.trim())
                .orElseThrow(() -> new RuntimeException("Invalid TO station: " + toStation));

        Ticket t = new Ticket();

        t.setFromStation(fromStation);
        t.setToStation(toStation);
        t.setStatus("ACTIVE");

        String fromLine = from.getLine() == null ? "" : from.getLine();
        String toLine = to.getLine() == null ? "" : to.getLine();

        t.setPrimaryLine(fromLine);
        t.setChangeRequired(!fromLine.equals(toLine));
        t.setIssuedAt(LocalDateTime.now());
        t.setValidUpto(LocalDateTime.now().plusHours(2));

        int stationCount = Math.abs(from.getSequenceNo() - to.getSequenceNo()) + 1;
        t.setFare(BigDecimal.valueOf(stationCount * 5));

        String rawData = t.getFromStation() + "|" + t.getToStation() + "|" + System.currentTimeMillis();

        String token = java.util.Base64.getEncoder()
                .encodeToString(rawData.getBytes());

        t.setQrToken(token);
        t.setQrCode(qrService.generateQrBytes(token));
        return ticketRepo.save(t);
    }

    /*
     * =====================
     * GET TICKET
     * =====================
     */
    public Ticket getTicketById(Long id) {
        return ticketRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    /*
     * =====================
     * GET QR
     * =====================
     */
    public byte[] getQr(Long id) {
        Ticket t = getTicketById(id);
        return t.getQrCode();
    }

    /*
     * =====================
     * VALIDATE
     * =====================
     */
    public Ticket validateTicket(Long id) {
        Ticket t = getTicketById(id);
        t.setStatus("USED");
        return ticketRepo.save(t);
    }
}