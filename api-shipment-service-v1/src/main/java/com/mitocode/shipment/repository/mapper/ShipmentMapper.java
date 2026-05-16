package com.mitocode.shipment.repository.mapper;

import com.mitocode.shipment.domain.Shipment;
import com.mitocode.shipment.infraestructure.entity.ShipmentEntity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ShipmentMapper {

    public static Shipment toDomain(ShipmentEntity entity) {
    	Shipment domain = new Shipment();
        domain.setId(entity.getId());
        domain.setOrderId(entity.getOrderId());
        domain.setAddress(entity.getAddress());
        domain.setLatitude(entity.getLatitude());
        domain.setLongitude(entity.getLongitude());
        domain.setReference(entity.getReference());
        domain.setStatus(entity.getStatus());
        domain.setShipmentCompany(ShipmentCompanyMapper.toDomain(entity.getShipmentCompany()));
        return domain;
    }

    public static ShipmentEntity toEntity(Shipment shipment) {
    	ShipmentEntity entity = new ShipmentEntity();
        entity.setId(shipment.getId());
        entity.setOrderId(shipment.getOrderId());
        entity.setAddress(shipment.getAddress());
        entity.setLatitude(shipment.getLatitude());
        entity.setLongitude(shipment.getLongitude());
        entity.setReference(shipment.getReference());
        entity.setShipmentCompany(ShipmentCompanyMapper.toEntity(shipment.getShipmentCompany()));
        entity.setStatus(shipment.getStatus());

        return entity;
    }
}
