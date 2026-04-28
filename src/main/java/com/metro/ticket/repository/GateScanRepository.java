package com.metro.ticket.repository;

import com.metro.ticket.model.GateScan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GateScanRepository extends JpaRepository<GateScan, Long> {
}
