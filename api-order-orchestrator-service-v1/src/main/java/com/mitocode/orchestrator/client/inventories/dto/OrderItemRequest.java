package com.mitocode.orchestrator.client.inventories.dto;

public record OrderItemRequest(
        Long productId,
        String productName,
        int quantity,
        String description
) {}
