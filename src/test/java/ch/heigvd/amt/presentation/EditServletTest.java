package ch.heigvd.amt.presentation;

import ch.heigvd.amt.buisness.IAuthenticationService;
import ch.heigvd.amt.integration.IClientDAO;
import ch.heigvd.amt.model.Client;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EditServletTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    EditServlet servlet;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    IClientDAO clientDAO;
    @Mock
    IAuthenticationService auth;
    @Mock
    HttpSession session;
    @Mock
    Client client;
    @Mock
    Client newclient;

    @BeforeEach
    public void setUp(){
        servlet = new EditServlet();
        servlet.clientDAO =clientDAO;
        servlet.auth =auth;

    }
    @Test
    void doPostWithTwoDifferentPasswordShouldsetErrorAndForward() throws ServletException, IOException {
        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getParameter("password-verif")).thenReturn("password");
        when(request.getRequestDispatcher("/WEB-INF/pages/edit.jsp")).thenReturn(dispatcher);
        servlet.doPost(request,response);
        verify(request,atLeastOnce()).setAttribute("error", "The passwords must be equal and at least 6 characters long.");
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    void doPostWithPasswordLengthLessThanSixShouldSetErrorAndForward() throws ServletException, IOException {
        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getParameter("password-verif")).thenReturn("pass");
        when(request.getRequestDispatcher("/WEB-INF/pages/edit.jsp")).thenReturn(dispatcher);
        servlet.doPost(request, response);
        verify(request,atLeastOnce()).setAttribute("error", "The passwords must be equal and at least 6 characters long.");
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    void doGetShouldRedirect() throws ServletException, IOException {
        when(request.getRequestDispatcher("/WEB-INF/pages/edit.jsp")).thenReturn(dispatcher);
        servlet.doGet(request,response);
        verify(dispatcher,atLeastOnce()).forward(request,response);

    }

    @Test
    void doPostLogoutWhenUpdateClient() throws ServletException, IOException {
        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("password-verif")).thenReturn("password");
        when(request.getContextPath()).thenReturn("contextPath");
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("client-session")).thenReturn(client);

        servlet.doPost(request,response);

        verify(response,atLeastOnce()).sendRedirect(request.getContextPath() + "/logout");


    }

}