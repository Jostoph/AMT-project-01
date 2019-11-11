package ch.heigvd.amt.presentation;

import ch.heigvd.amt.buisness.IAuthenticationService;
import ch.heigvd.amt.datastore.exceptions.DuplicateKeyException;
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

import java.io.IOException;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServletTest {
    @Mock
    HttpServletResponse response;
    @Mock
    HttpServletRequest request;
    @Mock
    IClientDAO clientDAO;
    @Mock
    RegistrationServlet servlet;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    Client client;
    @Mock
    IAuthenticationService auth;

    @BeforeEach
    void setup() {
        servlet = new RegistrationServlet();
        servlet.clientDAO = clientDAO;
        servlet.auth = auth;
    }

    @Test
    void doGetShouldForward() throws ServletException, IOException {
        when(request.getRequestDispatcher("/WEB-INF/pages/registration.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(dispatcher, atLeastOnce()).forward(request, response);

    }

    @Test
    void doPostShouldforwardWhenWrongAttribute() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("user");
        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("password-verif")).thenReturn("");
        when(request.getRequestDispatcher("/WEB-INF/pages/registration.jsp")).thenReturn(dispatcher);

        servlet.doPost(request,response);

        verify(request,atLeastOnce()).setAttribute("error","Please fill every field");
        verify(dispatcher,atLeastOnce()).forward(request,response);

    }

    @Test
    void doPostWithToShortUsername() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("12");
        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("password-verif")).thenReturn("password");
        when(request.getRequestDispatcher("/WEB-INF/pages/registration.jsp")).thenReturn(dispatcher);

        servlet.doPost(request,response);

        verify(request,atLeastOnce()).setAttribute("error","The username must be at least 4 character long.<br />");
        verify(dispatcher,atLeastOnce()).forward(request,response);

    }

    @Test
    void doPostWihToShordpassword() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("user");
        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("password")).thenReturn("123");
        when(request.getParameter("password-verif")).thenReturn("123");
        when(request.getRequestDispatcher("/WEB-INF/pages/registration.jsp")).thenReturn(dispatcher);

        servlet.doPost(request,response);

        verify(request,atLeastOnce()).setAttribute("error","The password needs to be at least 6 character long.<br />");
        verify(dispatcher,atLeastOnce()).forward(request,response);

    }

    @Test
    void doPostWihtwoDifferentPassword() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("user");
        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("password")).thenReturn("passowrd");
        when(request.getParameter("password-verif")).thenReturn("wrongPassword");
        when(request.getRequestDispatcher("/WEB-INF/pages/registration.jsp")).thenReturn(dispatcher);

        servlet.doPost(request,response);

        verify(request,atLeastOnce()).setAttribute("error","The passwords are not matching.");
        verify(dispatcher,atLeastOnce()).forward(request,response);
    }

    @Test
    void doPostShouldCreateClient() throws ServletException, IOException, DuplicateKeyException {
        when(request.getParameter("username")).thenReturn("user");
        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("password-verif")).thenReturn("password");
        when(request.getRequestDispatcher("/WEB-INF/pages/registration.jsp")).thenReturn(dispatcher);
        when(request.getContextPath()).thenReturn("ContextPath");

        servlet.doPost(request,response);
        verify(response,atLeastOnce()).sendRedirect(request.getContextPath() + "/login");

    }



}