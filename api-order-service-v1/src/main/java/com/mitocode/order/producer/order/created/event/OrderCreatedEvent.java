package com.mitocode.order.producer.order.created.event;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderCreatedEvent(UUID id, Customer customer, Warehouse warehouse, BigDecimal total, String status) {
}
