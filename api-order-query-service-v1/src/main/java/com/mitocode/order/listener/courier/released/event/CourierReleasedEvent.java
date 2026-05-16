package com.mitocode.order.listener.courier.released.event;

import lombok.Data;

@Data
public class CourierReleasedEvent {
    private String orderId;
    private String status;
}
