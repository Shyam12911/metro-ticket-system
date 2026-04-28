package com.metro.ticket.dto;

import java.math.BigDecimal;

public class AdminStatsDTO {
    public long totalTickets;
    public BigDecimal todaysRevenue;

    public AdminStatsDTO(long totalTickets, BigDecimal todaysRevenue) {
        this.totalTickets = totalTickets;
        this.todaysRevenue = todaysRevenue;
    }
}