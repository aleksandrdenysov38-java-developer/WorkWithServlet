package com.example.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class OrderServletTest {

    @Test
    void testDeleteMethod() throws Exception {

        OrderServlet servlet =
                new OrderServlet();

        HttpServletRequest request =
                mock(HttpServletRequest.class);

        HttpServletResponse response =
                mock(HttpServletResponse.class);

        when(request.getParameter("id"))
                .thenReturn("1");

        servlet.doDelete(request, response);

        verify(response)
                .setStatus(HttpServletResponse.SC_OK);
    }
}
