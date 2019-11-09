package ch.heigvd.amt.presentation;

import ch.heigvd.amt.datastore.exceptions.DuplicateKeyException;
import ch.heigvd.amt.integration.IClientDAO;
import ch.heigvd.amt.model.Client;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    @EJB
    IClientDAO clientDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get registration form
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordVerif = request.getParameter("password-verif");

        String errorMessage = "";

        if(username.isEmpty() || email.isEmpty() || password.isEmpty() || passwordVerif.isEmpty()) {
            errorMessage = "Please fill every field";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
        } else {

            if(username.length() < 4) {
                errorMessage += "The username must be at least 4 character long.<br />";
            }

            if(password.length() < 6) {
                errorMessage += "The password needs to be at least 6 character long.<br />";
            }

            if(!password.equals(passwordVerif)) {
                errorMessage += "The passwords are not matching.";
            }
        }

        if(errorMessage.isEmpty()) {
            try {
                Client client = new Client(username, email, password);

                clientDAO.create(client);

                response.sendRedirect(request.getContextPath() + "/login");
            } catch (DuplicateKeyException de) {
                errorMessage += "This username is already taken.";
            }
        }

        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
    }
}
