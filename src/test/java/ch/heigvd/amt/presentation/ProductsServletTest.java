package ch.heigvd.amt.presentation;

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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductsServletTest {
    @Mock
    HttpServletRequest request;
    @Mock
    Product product;
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


    @BeforeEach
    public void setup() {
        servlet = new ProductsServlet();
        servlet.productDAO = productDAO;
    }


    @Test
    void doGetShouldforward() throws ServletException, IOException {
        when(request.getRequestDispatcher("/WEB-INF/pages/products.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(dispatcher, atLeastOnce()).forward(request, response);
    }

    @Test
    void doGetShoulsettAllAtribute() throws ServletException, IOException {
        when(request.getParameter("pageNum")).thenReturn("3");
        when(productDAO.getNoRecords()).thenReturn(5);
        when(productDAO.getChunk(20, 10)).thenReturn(productList);
        when(request.getRequestDispatcher("/WEB-INF/pages/products.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);
        verify(request, atLeastOnce()).setAttribute("products", productList);
        verify(request, atLeastOnce()).setAttribute("pageNum", 3);
        verify(request, atLeastOnce()).setAttribute("totalNum", 5);

    }

}