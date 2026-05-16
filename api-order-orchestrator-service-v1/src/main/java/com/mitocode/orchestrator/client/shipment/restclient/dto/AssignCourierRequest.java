package com.mitocode.orchestrator.client.shipment.restclient.dto;

import java.util.UUID;

public record AssignCourierRequest(
        UUID orderId,
        ShipmentAddressRequest shipmentAddress,
        ShipmentCompanyRequest shipmentCompany
) {
}
