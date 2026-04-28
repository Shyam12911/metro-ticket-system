package com.metro.ticket.model;

import jakarta.persistence.*;

@Entity
@Table(name = "station", uniqueConstraints = @UniqueConstraint(columnNames = { "name", "line" }))
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String line;

    @Column(name = "sequence_no", nullable = false)
    private int sequenceNo;

    /* ===== GETTERS ===== */
    public int getSequenceNo1() {
        return sequenceNo;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLine() {
        return line;
    }

    public int getSequenceNo() {
        return sequenceNo;
    }

    /* ===== SETTERS ===== */

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }
}