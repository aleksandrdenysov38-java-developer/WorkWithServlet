package com.example.servlet;

import com.example.model.Order;
import com.example.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class OrderServlet extends HttpServlet {

    private final OrderRepository repository;
    private final ObjectMapper mapper;

    public OrderServlet() {
        this(new OrderRepository());
    }

    public OrderServlet(OrderRepository repository) {
        this.repository = repository;
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {

        Order order =
                mapper.readValue(req.getInputStream(), Order.class);

        Order createdOrder =
                repository.save(order);

        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.setContentType("application/json;charset=UTF-8");

        mapper.writeValue(
                resp.getOutputStream(),
                createdOrder
        );
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        Long id =
                Long.parseLong(req.getParameter("id"));

        Order order =
                repository.findById(id);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json;charset=UTF-8");

        mapper.writeValue(
                resp.getOutputStream(),
                order
        );
    }

    @Override
    protected void doPut(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        Order order =
                mapper.readValue(req.getInputStream(), Order.class);

        Order updatedOrder =
                repository.update(order);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json;charset=UTF-8");

        mapper.writeValue(
                resp.getOutputStream(),
                updatedOrder
        );
    }

    @Override
    protected void doDelete(HttpServletRequest req,
                            HttpServletResponse resp)
            throws IOException {

        Long id =
                Long.parseLong(req.getParameter("id"));

        repository.delete(id);

        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
