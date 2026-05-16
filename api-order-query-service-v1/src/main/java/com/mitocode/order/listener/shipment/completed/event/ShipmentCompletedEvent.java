package com.mitocode.order.listener.shipment.completed.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShipmentCompletedEvent {
    private String orderId;
    private String status;
}
