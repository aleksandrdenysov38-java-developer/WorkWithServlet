package com.example.servlet;

import com.example.model.Order;
import com.example.repository.OrderRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ReadListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.*;

public class OrderServletTest {

    private OrderRepository repository;
    private OrderServlet servlet;

    @BeforeEach
    void setUp() {
        repository = mock(OrderRepository.class);
        servlet = new OrderServlet(repository);
    }

    @Test
    void testDoGet() throws Exception {

        Order order = new Order();
        order.setId(1L);

        when(repository.findById(1L))
                .thenReturn(order);

        HttpServletRequest request =
                mock(HttpServletRequest.class);

        HttpServletResponse response =
                mock(HttpServletResponse.class);

        when(request.getParameter("id"))
                .thenReturn("1");

        ServletOutputStream outputStream =
                mock(ServletOutputStream.class);

        when(response.getOutputStream())
                .thenReturn(outputStream);

        servlet.doGet(request, response);

        verify(repository)
                .findById(1L);

        verify(response)
                .setStatus(HttpServletResponse.SC_OK);

        verify(response)
                .setContentType("application/json;charset=UTF-8");
    }

    @Test
    void testDoDelete() throws Exception {

        HttpServletRequest request =
                mock(HttpServletRequest.class);

        HttpServletResponse response =
                mock(HttpServletResponse.class);

        when(request.getParameter("id"))
                .thenReturn("1");

        servlet.doDelete(request, response);

        verify(repository)
                .delete(1L);

        verify(response)
                .setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    void testDoPost() throws Exception {

        String json =
                """
                {
                  "cost":1000
                }
                """;

        HttpServletRequest request =
                mock(HttpServletRequest.class);

        HttpServletResponse response =
                mock(HttpServletResponse.class);

        when(request.getInputStream())
                .thenReturn(
                        createInputStream(json)
                );

        ServletOutputStream outputStream =
                mock(ServletOutputStream.class);

        when(response.getOutputStream())
                .thenReturn(outputStream);

        Order savedOrder = new Order();
        savedOrder.setId(1L);

        when(repository.save(any(Order.class)))
                .thenReturn(savedOrder);

        servlet.doPost(request, response);

        verify(repository)
                .save(any(Order.class));

        verify(response)
                .setStatus(HttpServletResponse.SC_CREATED);

        verify(response)
                .setContentType("application/json;charset=UTF-8");
    }

    @Test
    void testDoPut() throws Exception {

        String json =
                """
                {
                  "id":1,
                  "cost":2000
                }
                """;

        HttpServletRequest request =
                mock(HttpServletRequest.class);

        HttpServletResponse response =
                mock(HttpServletResponse.class);

        when(request.getInputStream())
                .thenReturn(
                        createInputStream(json)
                );

        ServletOutputStream outputStream =
                mock(ServletOutputStream.class);

        when(response.getOutputStream())
                .thenReturn(outputStream);

        Order updatedOrder = new Order();
        updatedOrder.setId(1L);

        when(repository.update(any(Order.class)))
                .thenReturn(updatedOrder);

        servlet.doPut(request, response);

        verify(repository)
                .update(any(Order.class));

        verify(response)
                .setStatus(HttpServletResponse.SC_OK);

        verify(response)
                .setContentType("application/json;charset=UTF-8");
    }

    private ServletInputStream createInputStream(String json) {

        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(json.getBytes());

        return new ServletInputStream() {

            @Override
            public int read() {
                return inputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(
                    ReadListener readListener) {
            }
        };
    }
}
