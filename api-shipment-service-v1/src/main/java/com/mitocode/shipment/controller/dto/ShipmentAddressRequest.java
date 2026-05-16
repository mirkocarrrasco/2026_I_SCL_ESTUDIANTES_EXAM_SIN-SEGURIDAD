package com.mitocode.shipment.controller.dto;

public record ShipmentAddressRequest(
        String address,
        Double latitude,
        Double longitude,
        String reference
) {
}
