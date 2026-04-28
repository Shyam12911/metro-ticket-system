package com.metro.ticket.service;

import com.metro.ticket.model.MetroLine;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MetroLineService {

    private static final Set<String> BLUE_LINE = Set.of(
            "Vanaz", "Anand Nagar", "Ideal Colony", "Nal Stop",
            "Garware College", "Deccan Gymkhana",
            "Chhatrapati Sambhaji Udyan",
            "Civil Court", "Ruby Hall", "Bund Garden",
            "Yerwada", "Kalyani Nagar", "Ramwadi");

    private static final Set<String> PURPLE_LINE = Set.of(
            "PCMC", "Sant Tukaram Nagar", "Bhosari",
            "Kasarwadi", "Phugewadi", "Dapodi",
            "Bopodi", "Khadki", "Range Hill",
            "Shivajinagar", "Civil Court",
            "Budhwar Peth", "Mandai", "Swargate");

    public MetroLine getLine(String station) {
        if (BLUE_LINE.contains(station))
            return MetroLine.BLUE;
        if (PURPLE_LINE.contains(station))
            return MetroLine.PURPLE;
        throw new RuntimeException("Unknown station: " + station);
    }
}