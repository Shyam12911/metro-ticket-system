package com.metro.ticket.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metro.ticket.dto.GateScanRequest;
import com.metro.ticket.service.GateService;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/gate")
public class GateController {

    private final GateService service;

    public GateController(GateService service) {
        this.service = service;
    }

    @PostMapping("/scan")
    public ResponseEntity<?> scan(@RequestBody GateScanRequest req) {
        try {
            service.scan(
                    req.qrToken(),
                    req.scanType(),
                    req.station(),
                    req.platformNo(),
                    req.equipmentId());

            return ResponseEntity.ok("VALID");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}