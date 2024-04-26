package com.example.order.controllers;

import com.example.order.models.Order;
import com.example.order.models.OrderStatus;
import com.example.order.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long Id) {
        return ResponseEntity.ok().body(orderService.getOrderById(Id));
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok().body(orderService.createOrder(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order){
        return ResponseEntity.ok().body(orderService.updateOrder(id, order));
    }

    @PutMapping("/{Id}/status")
    public void updateOrderStatus(@PathVariable Long Id, @RequestParam OrderStatus status) {
        orderService.changeOrderStatus(Id, status);
    }
}
