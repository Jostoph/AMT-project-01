package ch.heigvd.amt.presentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogoutServletTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    LogoutServlet servlet;

    @BeforeEach
    public void setup(){
        servlet = new LogoutServlet();
    }

    @Test
    void doGetShouldInvalidYourSession() throws IOException {
        when(request.getSession()).thenReturn(session);
        servlet.doGet(request,response);
        verify(session,atLeastOnce()).invalidate();
    }

    @Test
    void doGetShouldRedirectToLogin() throws IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("contexPath");
        servlet.doGet(request,response);
        verify(response,atLeastOnce()).sendRedirect(request.getContextPath()+"/login");

    }

}