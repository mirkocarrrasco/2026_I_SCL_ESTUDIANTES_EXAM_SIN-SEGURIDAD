package com.mitocode.inventory.controller.mapper;

import com.mitocode.inventory.domain.Customer;
import com.mitocode.inventory.domain.Order;
import com.mitocode.inventory.domain.OrderItem;
import com.mitocode.inventory.domain.Warehouse;
import com.mitocode.inventory.dto.OrderItemResponse;
import com.mitocode.inventory.dto.ReserveOrderRequest;
import com.mitocode.inventory.dto.ReserveOrderResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderControllerMapper {

    public Order toDomain(ReserveOrderRequest request) {
    	Warehouse warehouse = new Warehouse(request.warehouseId(), null);
        Customer customer = new Customer(request.customerId());
        List<OrderItem> items = request.items().stream()
                .map(i -> new OrderItem(i.productId(), i.productName(), i.quantity(), i.description()))
                .toList();
        return new Order(request.orderId(), warehouse, customer, items, null, null);
    }

    public ReserveOrderResponse toResponse(Order order) {
        List<OrderItemResponse> items = order.getItems().stream()
                .map(i -> new OrderItemResponse(i.getProductId(), i.getProductName(), i.getQuantity(), i.getDescription()))
                .toList();
        return new ReserveOrderResponse(order.getOrderId(), order.getWarehouse().getId(),
                order.getCustomer().getCustomerId(), items);
    }
}
