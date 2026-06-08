package com.example.servlet;

import com.example.model.Order;
import com.example.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;



public class OrderServlet extends HttpServlet {

    private final OrderRepository repository = new OrderRepository();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        Order order =
                mapper.readValue(req.getInputStream(), Order.class);

        repository.save(order);

        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        Long id =
                Long.parseLong(req.getParameter("id"));

        Order order =
                repository.findById(id);

        resp.setContentType("application/json");

        mapper.writeValue(resp.getOutputStream(), order);
    }

    @Override
    protected void doPut(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        Order order =
                mapper.readValue(req.getInputStream(), Order.class);

        repository.update(order);

        resp.setStatus(HttpServletResponse.SC_OK);
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
