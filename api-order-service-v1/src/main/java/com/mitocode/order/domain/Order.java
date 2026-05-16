package com.mitocode.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Order {

    private UUID id;
    private Customer customer;
    private Warehouse warehouse;
    private BigDecimal total;
    private OrderStatus status;
    private String cancelReason;

    // Factory for creation
    public static Order createNew(Customer customer, Warehouse warehouse, BigDecimal total) {
        return new Order(null, customer, warehouse, total, OrderStatus.CREATED, null);
    }
}
