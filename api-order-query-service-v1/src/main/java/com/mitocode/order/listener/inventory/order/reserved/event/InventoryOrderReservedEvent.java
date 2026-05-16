package com.mitocode.order.listener.inventory.order.reserved.event;

import lombok.Data;

import java.util.List;

@Data
public class InventoryOrderReservedEvent {
    String orderId;
    List<OrderItemEvent> items;
}
