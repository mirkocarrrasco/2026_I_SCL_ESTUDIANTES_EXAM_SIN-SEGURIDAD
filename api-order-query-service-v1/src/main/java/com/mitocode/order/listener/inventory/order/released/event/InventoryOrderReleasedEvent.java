package com.mitocode.order.listener.inventory.order.released.event;

import lombok.Data;

@Data
public class InventoryOrderReleasedEvent {
    String orderId;
    String status;
    String reason;
}
