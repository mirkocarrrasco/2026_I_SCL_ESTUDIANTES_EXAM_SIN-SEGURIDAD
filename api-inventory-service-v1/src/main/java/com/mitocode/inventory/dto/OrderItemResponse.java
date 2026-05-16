package com.mitocode.inventory.dto;

public record OrderItemResponse(
        Long productId,
        String productName,
        int quantity,
        String description
) {
}
