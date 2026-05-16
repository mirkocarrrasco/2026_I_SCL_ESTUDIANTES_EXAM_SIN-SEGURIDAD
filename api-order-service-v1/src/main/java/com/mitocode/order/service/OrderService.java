package com.mitocode.order.service;

import com.mitocode.order.domain.Order;
import com.mitocode.order.domain.OrderStatus;
import com.mitocode.order.dto.request.CancelOrderRequest;
import com.mitocode.order.dto.request.UpdateOrderStatusRequest;
import com.mitocode.order.dto.response.OrderResponse;
import com.mitocode.order.mapper.DomainToResponseMapper;
import com.mitocode.order.metrics.OrderMetrics;
import com.mitocode.order.producer.order.cancelled.OrderCancelledProducer;
import com.mitocode.order.producer.order.created.OrderCreatedProducer;
import com.mitocode.order.producer.order.updated.OrderUpdatedProducer;
import com.mitocode.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderCreatedProducer orderCreatedProducer;
    private final OrderUpdatedProducer orderUpdatedProducer;
    private final OrderCancelledProducer orderCancelledProducer;
    private final OrderMetrics orderMetrics;

    @Transactional
    public Order create(Order domain) {

        domain.setId(UUID.randomUUID());

        Order orderSaved = orderRepository.save(domain);
        orderMetrics.incrementOrdersStatus(orderSaved.getStatus().name());
        orderCreatedProducer.produce(orderSaved);

        return orderSaved;
    }

    @Transactional
    public OrderResponse updateOrderStatus(UUID orderId, UpdateOrderStatusRequest request) {
        Optional<Order> opt = orderRepository.findById(orderId);
        if (opt.isEmpty()) {
            throw new IllegalArgumentException("Order not found: " + orderId);
        }

        Order domain = opt.get();

        // update entity (or convert to domain and apply business method)
        domain.setStatus(request.status());
        Order updated = orderRepository.save(domain);
        orderMetrics.incrementOrdersStatus(updated.getStatus().name());
        orderUpdatedProducer.produce(updated);
        // return response
        return DomainToResponseMapper.toResponse(updated);
    }

    public Order cancelOrder(UUID orderId, CancelOrderRequest request) {
        Order domain = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));

        domain.setStatus(OrderStatus.CANCELLED);
        domain.setCancelReason(request.reason());

        Order cancelledOrder = orderRepository.save(domain);
        orderMetrics.incrementOrdersStatus(cancelledOrder.getStatus().name());
        orderCancelledProducer.produce(cancelledOrder);

        return cancelledOrder;
    }
}
