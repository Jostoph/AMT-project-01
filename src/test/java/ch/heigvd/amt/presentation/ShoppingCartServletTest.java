package ch.heigvd.amt.presentation;

import ch.heigvd.amt.buisness.IAuthenticationService;
import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.integration.IClientDAO;
import ch.heigvd.amt.integration.IOrderDAO;
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
    ShoppingCartServlet servlet;

    @BeforeEach
    public void setup() throws IOException, KeyNotFoundException {
        servlet = new ShoppingCartServlet();

    }
    @Test
    void doGet(){


    }

}