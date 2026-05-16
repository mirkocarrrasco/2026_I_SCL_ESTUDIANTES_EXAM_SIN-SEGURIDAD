package com.mitocode.order.dto.request;

import java.math.BigDecimal;

public record CreateOrderRequest(CustomerRequest customer, WarehouseRequest warehouse, BigDecimal total) {
}
