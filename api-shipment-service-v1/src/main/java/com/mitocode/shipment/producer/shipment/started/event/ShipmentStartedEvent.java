package com.mitocode.shipment.producer.shipment.started.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShipmentStartedEvent {
    private String orderId;
    private String status;
}
