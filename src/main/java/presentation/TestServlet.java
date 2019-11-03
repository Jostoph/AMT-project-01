package presentation;

import datastore.exceptions.DuplicateKeyException;
import integration.IUsersDAO;
import model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestServlet", urlPatterns = "/test")
public class TestServlet extends HttpServlet {
    @EJB
    IUsersDAO usersDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = User.builder()
                .username("oliechti")
                .build();
        try {
            usersDAO.create(user);
            resp.getWriter().println("created user");
        } catch (Exception e) {
            resp.getWriter().println("Could not create user: " + e.getMessage());
        }
    }
}