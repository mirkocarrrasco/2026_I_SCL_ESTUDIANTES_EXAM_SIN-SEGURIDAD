package com.mitocode.orchestrator.client.shipment.restclient.dto;

public record ShipmentAddressRequest(
        String address,
        Double latitude,
        Double longitude,
        String reference
) {
}
