package com.mitocode.orchestrator.controller.dto;

public record ShipmentCompany(
        Long id,
        String name,
        String phoneNumber,
        String vehicleType,
        String licensePlate
) {}