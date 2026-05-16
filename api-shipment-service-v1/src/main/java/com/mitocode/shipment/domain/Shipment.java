package com.mitocode.shipment.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Shipment {
    private Long id;
    private UUID orderId;
    private String address;
    private Double latitude;
    private Double longitude;
    private String reference;
    private ShipmentCompany shipmentCompany;
    private ShipmentStatus status;
}
