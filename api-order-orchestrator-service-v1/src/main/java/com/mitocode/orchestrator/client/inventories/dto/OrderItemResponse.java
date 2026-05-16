package com.mitocode.orchestrator.client.inventories.dto;

public record OrderItemResponse(
        Long productId,
        String productName,
        int quantity,
        String description
) {
}