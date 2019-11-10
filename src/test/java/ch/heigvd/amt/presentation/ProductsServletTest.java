package ch.heigvd.amt.presentation;

import ch.heigvd.amt.integration.IProductDAO;
import ch.heigvd.amt.model.Client;
import ch.heigvd.amt.model.Order;
import ch.heigvd.amt.model.OrderLine;
import ch.heigvd.amt.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.event.CaretListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductsServletTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    IProductDAO productDAO;
    @Mock
    ProductsServlet servlet;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    List<Product> productList;
    @Mock
    HttpSession session;
    @Mock
    Order order;
    @Mock
    Client client;
    @Mock
    OrderLine orderLine;
    @Mock
    List<OrderLine> orderLines;


    @BeforeEach
    public void setup() {
        servlet = new ProductsServlet();
        servlet.productDAO = productDAO;
    }


    @Test
    void doGetShouldforward() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("order")).thenReturn(null);
        when(session.getAttribute("client-session")).thenReturn(client);
        when(client.getUsername()).thenReturn("user");
        when(request.getRequestDispatcher("/WEB-INF/pages/products.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(dispatcher, atLeastOnce()).forward(request, response);
    }

    @Test
    void doGetShoulSetAllAtribute() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("order")).thenReturn(order);
        when(session.getAttribute("client-session")).thenReturn(client);
        when(request.getParameter("pageNum")).thenReturn("3");
        when(productDAO.getNoRecords()).thenReturn(5);
        when(productDAO.getChunk(20, 10)).thenReturn(productList);
        when(request.getRequestDispatcher("/WEB-INF/pages/products.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);


        verify(request, atLeastOnce()).setAttribute("products", productList);
        verify(request, atLeastOnce()).setAttribute("pageNum", 3);
        verify(request, atLeastOnce()).setAttribute("totalNum", 5);
        verify(dispatcher, atLeastOnce()).forward(request, response);

    }

    @Test
    void doPostShouldReturnTheProducts() throws ServletException, IOException {
        when(request.getParameter("product_id")).thenReturn("1");
        when(request.getParameter("quantity")).thenReturn("32");
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("order")).thenReturn(order);
        when(order.getOrderLines()).thenReturn(orderLines);
        when(request.getContextPath()).thenReturn("contextPath");
        when(request.getParameter("currentPage")).thenReturn("currentPage");

        servlet.doPost(request,response);

        verify(response,atLeastOnce()).sendRedirect(request.getContextPath() + "/shop/products?pageNum=" + request.getParameter("currentPage"));

    }

//    @Test
//    void doPostWithInputErrorShouldRedirect() throws ServletException, IOException {
//        when(request.getParameter("product_id")).thenReturn("one");
//        when(request.getParameter("quantity")).thenReturn("dix");
//        when(request.getContextPath()).thenReturn("contextPath");
//
//        servlet.doPost(request,response);
//
//        verify(response,atLeastOnce()).sendRedirect(request.getContextPath());
//
//    }


}