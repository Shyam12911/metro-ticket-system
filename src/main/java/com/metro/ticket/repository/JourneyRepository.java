package com.metro.ticket.repository;

import com.metro.ticket.model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JourneyRepository extends JpaRepository<Journey, Long> {

    List<Journey> findBySourceIgnoreCaseOrderByDepartureTimeAsc(String source);

    List<Journey> findBySourceIgnoreCaseAndDestinationIgnoreCase(
        String source, String destination
    );
}
