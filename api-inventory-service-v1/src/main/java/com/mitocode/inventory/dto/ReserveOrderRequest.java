package com.mitocode.inventory.dto;

import java.util.List;
import java.util.UUID;

public record ReserveOrderRequest(
        UUID orderId,
        Long warehouseId,
        Long customerId,
        List<OrderItemRequest> items
) {}
