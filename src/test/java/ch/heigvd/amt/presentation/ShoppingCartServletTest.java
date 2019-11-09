package ch.heigvd.amt.presentation;

import ch.heigvd.amt.buisness.IAuthenticationService;
import ch.heigvd.amt.datastore.exceptions.DuplicateKeyException;
import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.integration.IClientDAO;
import ch.heigvd.amt.integration.IOrderDAO;
import ch.heigvd.amt.model.Client;
import ch.heigvd.amt.model.Order;
import ch.heigvd.amt.model.OrderLine;
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

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServletTest {
    @Mock
    HttpServletResponse response;

    @Mock
    HttpServletRequest request;

    @Mock
    IOrderDAO orderDAO;
    @Mock
    HttpSession session;

    @Mock
    Client client;
    @Mock
    RequestDispatcher requestDispatcher;

    @Mock
    IAuthenticationService auth;
    @Mock
    List<OrderLine> orderLines;
    @Mock
    Order order;
    @Mock
    RequestDispatcher dispatcher;

    @Mock
    ShoppingCartServlet servlet;

    @BeforeEach
    public void setup() throws IOException, KeyNotFoundException {
        servlet = new ShoppingCartServlet();
        servlet.orderDAO = orderDAO;

    }

    @Test
    void doGetWithOrderShouldForwardWithAttribute() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("order")).thenReturn(order);
        when(request.getRequestDispatcher("/WEB-INF/pages/cart.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request, atLeastOnce()).setAttribute("order", order);
        verify(dispatcher, atLeastOnce()).forward(request, response);

    }

    @Test
    void doGetWtithoutOrderShouldRedirect() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("order")).thenReturn(null);
        when(request.getContextPath()).thenReturn("contextPath");

        servlet.doGet(request, response);

        verify(response, atLeastOnce()).sendRedirect("contextPath" + "/shop/products");

    }

    @Test
    void doPostWhenCancelCommandShouldRemoveOrderAndRedirect() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("order")).thenReturn(order);
        when(request.getParameter("command")).thenReturn(null);
        when(request.getParameter("cancel")).thenReturn("cancel");
        when(request.getContextPath()).thenReturn("contextPath");

        servlet.doPost(request, response);

        verify(session, atLeastOnce()).removeAttribute("order");
        verify(response, atLeastOnce()).sendRedirect(request.getContextPath() + "/shop/shop");
    }

    @Test
    void doPostWithCommandShouldCreateAndOrder() throws ServletException, IOException, DuplicateKeyException {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("order")).thenReturn(order);
        when(order.getOrderLines()).thenReturn(orderLines);
        when(request.getParameter("command")).thenReturn("command");
        when(request.getContextPath()).thenReturn("contextPath");

        servlet.doPost(request, response);

        verify(orderDAO, atLeastOnce()).create(order);
        verify(session, atLeastOnce()).removeAttribute("order");
        verify(response, atLeastOnce()).sendRedirect(request.getContextPath() + "/shop/profile");

    }


    @Test
    void doPostWithDuplicateOrderShouldRedirect() throws ServletException, IOException, DuplicateKeyException {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("order")).thenReturn(order);
        when(order.getOrderLines()).thenReturn(orderLines);
        when(request.getParameter("command")).thenReturn("command");
        when(request.getContextPath()).thenReturn("contextPath");
        when(orderDAO.create(order)).thenThrow(DuplicateKeyException.class);

        servlet.doPost(request, response);

        verify(response, atLeastOnce()).sendRedirect(request.getContextPath() + "/shop/products");

    }

}