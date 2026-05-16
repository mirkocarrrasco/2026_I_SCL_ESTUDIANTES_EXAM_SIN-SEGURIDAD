package com.mitocode.shipment.repository.mapper;

import com.mitocode.shipment.domain.ShipmentCompany;
import com.mitocode.shipment.infraestructure.entity.ShipmentCompanyEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ShipmentCompanyMapper {

    public static ShipmentCompany toDomain(ShipmentCompanyEntity entity) {
    	ShipmentCompany shipmentCompany = new ShipmentCompany();
        shipmentCompany.setId(entity.getId());
        shipmentCompany.setName(entity.getName());
        shipmentCompany.setPhoneNumber(entity.getPhoneNumber());
        shipmentCompany.setEmail(entity.getEmail());
        shipmentCompany.setWebSite(entity.getWebSite());
        return shipmentCompany;
    }

    public static ShipmentCompanyEntity toEntity(ShipmentCompany shipmentCompany) {
    	ShipmentCompanyEntity entity = new ShipmentCompanyEntity();
        entity.setId(shipmentCompany.getId());
        entity.setName(shipmentCompany.getName());
        entity.setPhoneNumber(shipmentCompany.getPhoneNumber());
        entity.setEmail(shipmentCompany.getEmail());
        entity.setWebSite(shipmentCompany.getWebSite());
        return entity;
    }
}
