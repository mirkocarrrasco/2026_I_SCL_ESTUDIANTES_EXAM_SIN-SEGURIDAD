package com.mitocode.inventory.producer.inventory.order.released.event;

import lombok.Data;

@Data
public class InventoryOrderCancelledEvent {
    String orderId;
    String status;
    String reason;
}
