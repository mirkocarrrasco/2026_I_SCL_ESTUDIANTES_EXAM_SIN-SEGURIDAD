package com.mitocode.shipment.infraestructure.repository;

import com.mitocode.shipment.infraestructure.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ShipmentRepositoryJpa extends JpaRepository<ShipmentEntity, Long> {

    Optional<ShipmentEntity> findByOrderId(UUID orderId);

    @Query("SELECT d FROM ShipmentEntity d JOIN FETCH d.shipmentCompany WHERE d.id = :id")
    Optional<ShipmentEntity> findByIdWithShipmentCompany(@Param("id") Long id);
}

