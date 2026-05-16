package com.mitocode.inventory.repository;

import com.mitocode.inventory.domain.Order;
import com.mitocode.inventory.infraestructure.entity.OrderEntity;
import com.mitocode.inventory.infraestructure.repository.OrderJpaRepository;
import com.mitocode.inventory.repository.mapper.OrderRepositoryMapper;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class OrderRepository {

    private final OrderJpaRepository jpaRepository;
    private final OrderRepositoryMapper mapper;

    public OrderRepository(OrderJpaRepository jpaRepository, OrderRepositoryMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    public Order getBy(Long warehouseId, UUID orderId) {
        OrderEntity entity = jpaRepository.findByWarehouseIdAndId(warehouseId, orderId);
        return mapper.toDomain(entity);
    }

    public Order save(Order order) {
        OrderEntity entity = mapper.toEntity(order);
        OrderEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }
}
