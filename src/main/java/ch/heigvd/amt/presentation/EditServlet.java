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
import java.io.IOException;

@WebServlet(name = "EditServlet", urlPatterns = "/shop/edit")
public class EditServlet extends HttpServlet {

    @EJB
    IClientDAO clientDAO;

    @EJB
    IAuthenticationService auth;

    // TODO add delete account ?
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get modification form
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordVerif = request.getParameter("password-verif");

        if(!password.isEmpty()) {

            if(!password.equals(passwordVerif)) {
                request.setAttribute("error", "The passwords are not equal.");
            }

            if(password.length() < 6) {
                request.setAttribute("error", "The password must be at least 6 characters long.");
            }
            request.getRequestDispatcher("/WEB-INF/pages/edit.jsp").forward(request, response);
        }

        Client current = (Client) request.getSession(false).getAttribute("client-session");

        Client updatedClient = new Client(current.getUsername(),
                email.isEmpty() ? current.getEmail() : email,
                password.isEmpty() ? current.getPassword() : auth.hashPassword(password));

        try {
            clientDAO.update(updatedClient);
        } catch (KeyNotFoundException e) {
            request.setAttribute("error","The update failed");
            request.getRequestDispatcher("/WEB-INF/pages/edit.jsp").forward(request, response);
        }

        response.sendRedirect(request.getContextPath() + "/logout");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/edit.jsp").forward(request, response);
    }
}
