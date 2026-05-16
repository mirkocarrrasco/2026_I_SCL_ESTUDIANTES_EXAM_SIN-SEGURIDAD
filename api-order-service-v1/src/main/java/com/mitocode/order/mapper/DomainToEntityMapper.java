package com.mitocode.order.mapper;

import com.mitocode.order.domain.Order;
import com.mitocode.order.infraestructure.entity.OrderEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DomainToEntityMapper {

    public static OrderEntity toEntity(Order domain) {
        OrderEntity entity = new OrderEntity();
        entity.setId(domain.getId());
        entity.setCustomerId(domain.getCustomer().getId());
        entity.setCustomerName(domain.getCustomer().getName());
        entity.setWarehouseId(domain.getWarehouse().getId());
        entity.setWarehouseName(domain.getWarehouse().getName());
        entity.setTotal(domain.getTotal());
        entity.setStatus(domain.getStatus());
        entity.setCancelReason(domain.getCancelReason());
        return entity;
    }
}
