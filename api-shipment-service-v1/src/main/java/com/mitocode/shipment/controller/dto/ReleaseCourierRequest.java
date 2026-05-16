package com.mitocode.shipment.controller.dto;

import java.util.UUID;

public record ReleaseCourierRequest(
        UUID orderId
) {
}
