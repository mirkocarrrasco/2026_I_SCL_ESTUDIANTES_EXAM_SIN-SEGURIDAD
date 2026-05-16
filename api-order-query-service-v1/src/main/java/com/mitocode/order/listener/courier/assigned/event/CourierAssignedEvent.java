package com.mitocode.order.listener.courier.assigned.event;

import lombok.Data;

@Data
public class CourierAssignedEvent {
    private String orderId;
    private String address;
    private Double latitude;
    private Double longitude;
    private String reference;
    private ShipmentCompanyEvent shipmentCompany;
    private String status;
}
