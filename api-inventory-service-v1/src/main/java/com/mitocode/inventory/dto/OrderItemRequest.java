package com.mitocode.inventory.dto;

public record OrderItemRequest(
        Long productId,
        String productName,
        int quantity,
        String description
) {}
