package com.mitocode.inventory.infraestructure.repository;

import com.mitocode.inventory.infraestructure.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {

    OrderEntity findByWarehouseIdAndId(Long warehouseId, UUID orderId);

}
