package com.mitocode.inventory.dto;

import java.util.UUID;

public record ReleaseOrderRequest(
        UUID orderId
) {
}
