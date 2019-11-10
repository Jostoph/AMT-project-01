package ch.heigvd.amt.presentation;

import ch.heigvd.amt.buisness.IAuthenticationService;
import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.integration.IClientDAO;
import ch.heigvd.amt.integration.IOrderDAO;
import ch.heigvd.amt.integration.IProductDAO;
import ch.heigvd.amt.model.Client;
import ch.heigvd.amt.model.Order;
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
class ProfileServletTest {
    @Mock
    HttpServletResponse response;

    @Mock
    HttpServletRequest request;
    @Mock
    IOrderDAO orderDAO;

    @Mock
    IProductDAO productDAO;

    @Mock
    IClientDAO clientDAO;

    @Mock
    ProfileServlet servlet;
    @Mock
    HttpSession session;
    @Mock
    Client client;
    @Mock
    List<Order> orderList;
    @Mock
    RequestDispatcher dispatcher;

    @BeforeEach
    public void setUp() {
        servlet = new ProfileServlet();
        servlet.clientDAO = clientDAO;
        servlet.productDAO = productDAO;
        servlet.orderDAO = orderDAO;

    }

    @Test
    void doPostShouldDeleteClient() throws ServletException, IOException, KeyNotFoundException {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("client-session")).thenReturn(client);
        when(request.getParameter("delete")).thenReturn("can you delete");
        when(client.getUsername()).thenReturn("username");
        when(request.getContextPath()).thenReturn("contextPath");

        servlet.doPost(request, response);

        verify(clientDAO, atLeastOnce()).deleteById(client.getUsername());
        verify(session, atLeastOnce()).invalidate();
        verify(response, atLeastOnce()).sendRedirect(request.getContextPath());

    }

    @Test
    void doGetShoulSetAttribute() throws KeyNotFoundException, ServletException, IOException {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("client-session")).thenReturn(client);
        when(client.getUsername()).thenReturn("username");
        when(client.getEmail()).thenReturn("email");
        when( request.getRequestDispatcher("/WEB-INF/pages/profile.jsp")).thenReturn(dispatcher);

        servlet.doGet(request,response);

        verify(request,atLeastOnce()).setAttribute("username", client.getUsername());
        verify(request,atLeastOnce()).setAttribute("email", client.getEmail());
        verify(dispatcher).forward(request,response);
    }



}