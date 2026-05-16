package com.mitocode.order.repository;

import com.mitocode.order.domain.Order;
import com.mitocode.order.infraestructure.entity.OrderEntity;
import com.mitocode.order.infraestructure.repository.OrderRepositoryJPA;
import com.mitocode.order.mapper.DomainToEntityMapper;
import com.mitocode.order.mapper.EntityToDomainMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class OrderRepository {

    private final OrderRepositoryJPA orderRepository;

    public Optional<Order> findById(UUID id) {
        return orderRepository.findById(id)
                .map(EntityToDomainMapper::toDomain);
    }

    public Order save(Order order) {
        OrderEntity entity = DomainToEntityMapper.toEntity(order);

        entity = orderRepository.save(entity);

        return EntityToDomainMapper.toDomain(entity);
    }

}
