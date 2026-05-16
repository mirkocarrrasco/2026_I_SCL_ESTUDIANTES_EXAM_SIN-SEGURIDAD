package com.mitocode.shipment.producer.courier.released.event;

import lombok.Data;

@Data
public class CourierReleasedEvent {
    private String orderId;
    private String status;
}
