package com.mitocode.shipment.controller.dto;

import java.util.UUID;

public record AssignCourierRequest(
        UUID orderId,
        ShipmentAddressRequest shipmentAddress,
        ShipmentCompanyRequest shipmentCompany
) {
}
