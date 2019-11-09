package ch.heigvd.amt.presentation;

import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.integration.IProductDAO;
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

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServletTest {
    @Mock
    HttpServletRequest request;
    @Mock
    Product product;
    @Mock
    HttpServletResponse response;
    @Mock
    IProductDAO productDAO;
    @Mock
    ProductServlet servlet;
    @Mock
    RequestDispatcher dispatcher;


    @BeforeEach
    public void setup() {
        servlet = new ProductServlet();
        servlet.productDAO = productDAO;
    }

    @Test
    void doPost() {
    }

    @Test
    void doGetShouldsetProductInRequest() throws KeyNotFoundException, ServletException, IOException {
        when(request.getParameter("product_id")).thenReturn("42");
        when(productDAO.findById(42)).thenReturn(product);
        when(request.getRequestDispatcher("/WEB-INF/pages/product.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(request, atLeastOnce()).setAttribute("product", product);
    }


    @Test
    void doGetShouldRedirect() throws KeyNotFoundException, ServletException, IOException {
        when(request.getParameter("product_id")).thenReturn("42");
        when(productDAO.findById(42)).thenReturn(product);
        when(request.getRequestDispatcher("/WEB-INF/pages/product.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(dispatcher, atLeastOnce()).forward(request, response);
    }

    @Test
    void doGetWithWrongIDFormatShouldRedirectToProducts() throws ServletException, IOException {
        when(request.getParameter("product_id")).thenReturn("one");
        servlet.doGet(request, response);
        verify(response, atLeastOnce()).sendRedirect(request.getContextPath() + "/shop/products");
    }

    @Test
    void doGetWithWrongIDShouldRedirectToProducts() throws KeyNotFoundException, ServletException, IOException {
        when(request.getParameter("product_id")).thenReturn("-10");
        when(productDAO.findById(-10)).thenThrow(KeyNotFoundException.class);
        servlet.doGet(request, response);
        verify(response, atLeastOnce()).sendRedirect(request.getContextPath() + "/shop/products");
    }


}