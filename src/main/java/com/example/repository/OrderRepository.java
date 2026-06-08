package com.example.repository;

import com.example.model.Order;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class OrderRepository {

    private final Map<Long, Order> orders = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Order save(Order order) {

        Long id = idGenerator.getAndIncrement();

        order.setId(id);

        orders.put(id, order);

        return order;
    }

    public Order findById(Long id) {
        return orders.get(id);
    }

    public Order update(Order order) {

        orders.put(order.getId(), order);

        return order;
    }

    public void delete(Long id) {
        orders.remove(id);
    }
}
