package com.example.order.services;

import com.example.order.models.Order;
import com.example.order.models.OrderStatus;
import com.example.order.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    OrderRepository orderRepository;
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public Order createOrder(Order order) {
        order.setStatus(OrderStatus.IN_PROGRESS);
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order newOrder){
        Order order = orderRepository.findById(id).orElse(null);
        if(order != null) {
            order.setClientId(newOrder.getClientId());
            order.setProdId(newOrder.getProdId());
            order.setQuantity(newOrder.getQuantity());
            order.setStatus(newOrder.getStatus());
            return orderRepository.save(order);
        }
        return null;

    }

    public void changeOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(newStatus);
            orderRepository.save(order);
        }
    }
}
