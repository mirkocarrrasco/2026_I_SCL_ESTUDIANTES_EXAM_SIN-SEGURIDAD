package com.mitocode.inventory.repository.mapper;

import com.mitocode.inventory.domain.Customer;
import com.mitocode.inventory.domain.Order;
import com.mitocode.inventory.domain.OrderItem;
import com.mitocode.inventory.domain.Warehouse;
import com.mitocode.inventory.infraestructure.entity.OrderEntity;
import com.mitocode.inventory.infraestructure.entity.OrderItemEntity;
import com.mitocode.inventory.infraestructure.entity.WarehouseEntity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderRepositoryMapper {

    public OrderEntity toEntity(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getOrderId());
        entity.setWarehouse(new WarehouseEntity());
        entity.getWarehouse().setId(order.getWarehouse().getId());
        entity.setCustomerId(order.getCustomer().getCustomerId());
        entity.setStatus(order.getStatus());
        entity.setReason(order.getReason());

        List<OrderItemEntity> itemEntities = order.getItems().stream().map(i -> {
            OrderItemEntity e = new OrderItemEntity();
            e.setProductId(i.getProductId());
            e.setProductName(i.getProductName());
            e.setQuantity(i.getQuantity());
            e.setDescription(i.getDescription());
            e.setOrder(entity);
            return e;
        }).toList();

        entity.setItems(itemEntities);
        return entity;
    }

    public Order toDomain(OrderEntity entity) {
    	Warehouse warehouse = new Warehouse(entity.getWarehouse().getId(), entity.getWarehouse().getName());
        Customer customer = new Customer(entity.getCustomerId());

        List<OrderItem> items = entity.getItems().stream()
                .map(e -> new OrderItem(e.getProductId(), e.getProductName(), e.getQuantity(), e.getDescription()))
                .toList();

        return new Order(entity.getId(), warehouse, customer, items, entity.getStatus(), entity.getReason());
    }


}
