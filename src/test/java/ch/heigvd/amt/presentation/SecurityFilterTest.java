package ch.heigvd.amt.presentation;

import ch.heigvd.amt.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecurityFilterTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    SecurityFilter filter;
    @Mock
    HttpSession session;
    @Mock
    FilterChain chain;

    @Mock
    Client client;

    @BeforeEach
    public void setUp() {
        filter = new SecurityFilter();

    }

    @Test
    public void doFilterShouldContinueChainWhenSessionIsValid() throws IOException, ServletException {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("client-session")).thenReturn(client);

        filter.doFilter(request, response, chain);

        verify(chain, atLeastOnce()).doFilter(request, response);
    }

    @Test
    public void doFilterShouldRedirectWhenSessionIsNull() throws IOException, ServletException {
        when(request.getSession(false)).thenReturn(null);
        when(request.getContextPath()).thenReturn("contexPath");

        filter.doFilter(request, response, chain);

        verify(response, atLeastOnce()).sendRedirect(request.getContextPath() + "/login");

    }

    @Test
    public void doFilterShouldRedirectWhenSessionAttributIsNull() throws IOException, ServletException {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("client-session")).thenReturn(null);
        when(request.getContextPath()).thenReturn("contexPath");

        filter.doFilter(request, response, chain);

        verify(response, atLeastOnce()).sendRedirect(request.getContextPath() + "/login");

    }

}