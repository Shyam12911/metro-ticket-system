package com.metro.ticket.dto;

public record GateScanRequest(
                String qrToken,
                String scanType,
                String station,
                int platformNo,
                String equipmentId) {
}
