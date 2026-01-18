package com.metro.ticket.dto;

public class BookingRequest {
    private Long userId;
    private Long journeyId;

    public Long getUserId() {
        return userId;
    }

    public Long getJourneyId() {
        return journeyId;
    }
}
