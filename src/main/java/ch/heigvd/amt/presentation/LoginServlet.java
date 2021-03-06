package ch.heigvd.amt.presentation;

import ch.heigvd.amt.buisness.IAuthenticationService;
import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.integration.IClientDAO;
import ch.heigvd.amt.model.Client;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @EJB
    IClientDAO clientDAO;

    @EJB
    IAuthenticationService auth;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get login form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {

            // search client in database
            Client client = clientDAO.findById(username);


            // check credentials
            if(auth.checkPassword(password, client.getPassword())) {

                // create user session
                HttpSession session = request.getSession(true);
                session.setAttribute("client-session", client);

                // redirect to products page
                response.sendRedirect(request.getContextPath() + "/shop/products");
            } else {

                // invalid password
                // invalid credentials, send error
                request.setAttribute("invalid", "Invalid Credentials");
                request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            }

        } catch (KeyNotFoundException e) {

            // client not in database
            // invalid credentials, send error
            request.setAttribute("invalid", "Invalid Credentials");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }
}
