package ch.heigvd.amt.presentation;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/shop/*")
public class SecurityFilter implements Filter {

    // The security filter is denying access to unauthorized (not singed in) users to every page after /shop/
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // Get user session without creating one
        HttpSession session = req.getSession(false);


        if (session == null || session.getAttribute("client-session") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            chain.doFilter(req, resp);
        }
    }

}
