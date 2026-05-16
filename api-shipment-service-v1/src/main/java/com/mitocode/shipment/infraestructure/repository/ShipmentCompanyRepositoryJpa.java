package com.mitocode.shipment.infraestructure.repository;

import com.mitocode.shipment.infraestructure.entity.ShipmentCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentCompanyRepositoryJpa extends JpaRepository<ShipmentCompanyEntity, Long> {
}