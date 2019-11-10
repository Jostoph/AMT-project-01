package ch.heigvd.amt.presentation;

import ch.heigvd.amt.buisness.IAuthenticationService;
import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
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
class LoginServletTest {
    @Mock
    HttpServletResponse response;

    @Mock
    HttpServletRequest request;

    @Mock
    IClientDAO clientDAO;
    @Mock
    HttpSession session;

    @Mock
    Client client;
    @Mock
    RequestDispatcher requestDispatcher;

    @Mock
    IAuthenticationService auth;

    @Mock
    LoginServlet servlet;

    @BeforeEach
    public void setup() throws IOException, KeyNotFoundException {
        servlet = new LoginServlet();
        servlet.clientDAO = clientDAO;
        servlet.auth = auth;
    }

    @Test
    void doGetShouldRedirect() throws KeyNotFoundException, ServletException, IOException {
        when(request.getRequestDispatcher("/WEB-INF/pages/login.jsp")).thenReturn(requestDispatcher);
        servlet.doGet(request, response);
        verify(requestDispatcher, atLeastOnce()).forward(request, response);
    }


    @Test
    void doPostWithGoodCredentialRedirectToShop() throws KeyNotFoundException, ServletException, IOException {
        when(request.getParameter("username")).thenReturn("username");
        when(request.getParameter("password")).thenReturn("password");
        when(clientDAO.findById("username")).thenReturn(client);
        when(client.getPassword()).thenReturn("hashpassword");
        when(auth.checkPassword("password", "hashpassword")).thenReturn(true);
        when(request.getSession(true)).thenReturn(session);
        servlet.doPost(request, response);
        verify(response, atLeastOnce()).sendRedirect(request.getContextPath() + "/shop/products");
    }

    @Test
    void doPostWithWrongPasswordShouldDisplayError() throws KeyNotFoundException, ServletException, IOException {
        when(request.getParameter("username")).thenReturn("username");
        when(request.getParameter("password")).thenReturn("wrongPassword");
        when(clientDAO.findById("username")).thenReturn(client);
        when(client.getPassword()).thenReturn("hashpassword");
        when(auth.checkPassword("wrongPassword", "hashpassword")).thenReturn(false);
        when(request.getRequestDispatcher("/WEB-INF/pages/login.jsp")).thenReturn(requestDispatcher);
        servlet.doPost(request, response);
        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/pages/login.jsp");
        verify(requestDispatcher, atLeastOnce()).forward(request, response);

    }

    @Test
    void doPostWithWrongUserHaveToRedirectToRegistration() throws KeyNotFoundException, IOException, ServletException {
        when(request.getParameter("username")).thenReturn("wrongUser");
        when(clientDAO.findById("wrongUser")).thenThrow(KeyNotFoundException.class);
        when(request.getRequestDispatcher("/WEB-INF/pages/login.jsp")).thenReturn(requestDispatcher);
        servlet.doPost(request,response);
        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/pages/login.jsp");
        verify(requestDispatcher, atLeastOnce()).forward(request, response);
    }

}