package com.mitocode.shipment.controller.mapper;

import com.mitocode.shipment.controller.dto.AssignCourierRequest;
import com.mitocode.shipment.controller.dto.ShipmentResponse;
import com.mitocode.shipment.domain.Shipment;
import com.mitocode.shipment.domain.ShipmentCompany;

public class ShipmentMapper {

    public static Shipment toDomain(AssignCourierRequest request) {

    	Shipment domain = new Shipment();
        domain.setOrderId(request.orderId());
        domain.setAddress(request.shipmentAddress().address());
        domain.setLatitude(request.shipmentAddress().latitude());
        domain.setLongitude(request.shipmentAddress().longitude());
        domain.setReference(request.shipmentAddress().reference());
        domain.setShipmentCompany(new ShipmentCompany(request.shipmentCompany().id()));

        return domain;
    }

    public static ShipmentResponse toResponse(Shipment shipment) {
        return new ShipmentResponse(
                shipment.getId(),
                shipment.getOrderId(),
                shipment.getAddress(),
                shipment.getStatus().name(),
                shipment.getShipmentCompany().getName()
        );
    }
}
