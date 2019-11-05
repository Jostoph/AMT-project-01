package presentation;

import integration.IClientDAO;
import model.Client;

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
    IClientDAO clientDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = new Client("Bob", "bob@fakemail.com", "123456");
        try {
            clientDAO.create(client);
            resp.getWriter().println("create client");
        } catch (Exception e) {
            resp.getWriter().println("Could not create client : " + e.getMessage());
        }
    }
}
