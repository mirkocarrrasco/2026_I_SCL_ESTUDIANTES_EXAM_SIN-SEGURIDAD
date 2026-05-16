package com.mitocode.order.mapper;

import com.mitocode.order.domain.Customer;
import com.mitocode.order.domain.Order;
import com.mitocode.order.domain.Warehouse;
import com.mitocode.order.infraestructure.entity.OrderEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityToDomainMapper {

    public static Order toDomain(OrderEntity entity) {
        return new Order(
                entity.getId(),
                Customer.of(entity.getCustomerId(), entity.getCustomerName()),
                Warehouse.of(entity.getWarehouseId(), entity.getWarehouseName()),
                entity.getTotal(),
                entity.getStatus(),
                entity.getCancelReason()
        );
    }
}
