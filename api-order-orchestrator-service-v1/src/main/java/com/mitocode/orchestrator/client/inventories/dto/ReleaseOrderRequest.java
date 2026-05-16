package com.mitocode.orchestrator.client.inventories.dto;

import java.util.UUID;

public record ReleaseOrderRequest(
        UUID orderId
) {
}
