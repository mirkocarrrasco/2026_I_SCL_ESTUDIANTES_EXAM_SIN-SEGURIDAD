package com.mitocode.shipment.producer.courier.assigned.event;

import lombok.Data;

@Data
public class ShipmentCompanyEvent {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String webSite;
}

