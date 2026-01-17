package com.metro.ticket.model;

import jakarta.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Journey journey;

    @ManyToOne
    private User user;

    private int seatCount;
    private double totalPrice;

    // ===== SETTERS =====
    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // ===== GETTERS =====
    public Long getId() {
        return id;
    }

    public Journey getJourney() {
        return journey;
    }

    public User getUser() {
        return user;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
