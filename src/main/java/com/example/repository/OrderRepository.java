package com.example.repository;

import com.example.model.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository {

    private static final Map<Long, Order> ORDERS = new HashMap<>();

    public void save(Order order) {
        ORDERS.put(order.getId(), order);
    }

    public Order findById(Long id) {
        return ORDERS.get(id);
    }

    public void update(Order order) {
        ORDERS.put(order.getId(), order);
    }

    public void delete(Long id) {
        ORDERS.remove(id);
    }
}
