package com.mitocode.inventory.dto;

import java.util.List;
import java.util.UUID;

public record ReserveOrderResponse(
        UUID orderId,
        Long warehouseId,
        Long customerId,
        List<OrderItemResponse> items
) {
}
