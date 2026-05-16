package com.mitocode.shipment.controller.dto;

import java.util.UUID;

public record ShipmentResponse (
        Long id,
        UUID orderId,
        String address,
        String status,
        String shipmentCompanyName
) {
}
