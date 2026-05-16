package com.mitocode.shipment.repository;

import com.mitocode.shipment.domain.Shipment;
import com.mitocode.shipment.infraestructure.entity.ShipmentEntity;
import com.mitocode.shipment.infraestructure.repository.ShipmentRepositoryJpa;
import com.mitocode.shipment.repository.mapper.ShipmentMapper;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ShipmentRepository {

    private final ShipmentRepositoryJpa shipmentRepository;
    private final EntityManager entityManager;

    public Optional<Shipment> findByIdWithShipmentCompany(Long id) {
        return shipmentRepository.findByIdWithShipmentCompany(id).map(ShipmentMapper::toDomain);
    }

    public Optional<Shipment> getByOrderId(UUID orderId) {
        return shipmentRepository.findByOrderId(orderId).map(ShipmentMapper::toDomain);
    }

    public Shipment save(Shipment shipment) {

        ShipmentEntity saved = shipmentRepository.save(ShipmentMapper.toEntity(shipment));

        // Refrescar para asegurarte que la relación está completamente cargada
        entityManager.flush();
        entityManager.refresh(saved);

        return ShipmentMapper.toDomain(saved);
    }

    public Shipment update(Shipment shipment) {

        ShipmentEntity updated = shipmentRepository.save(ShipmentMapper.toEntity(shipment));

        return ShipmentMapper.toDomain(updated);
    }
}
