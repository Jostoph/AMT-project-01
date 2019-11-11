package ch.heigvd.amt.presentation;

import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.integration.IClientDAO;
import ch.heigvd.amt.integration.IOrderDAO;
import ch.heigvd.amt.integration.IProductDAO;
import ch.heigvd.amt.model.Client;
import ch.heigvd.amt.model.Order;
import ch.heigvd.amt.model.OrderLine;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ProfileServlet", urlPatterns = "/shop/profile")
public class ProfileServlet extends HttpServlet {

    @EJB
    IOrderDAO orderDAO;

    @EJB
    IProductDAO productDAO;

    @EJB
    IClientDAO clientDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // get the Client session information
        Client currentClient = (Client) session.getAttribute("client-session");

        // request to delete the account
        if(request.getParameter("delete") != null && currentClient != null) {
            // delete client account
            try {
                clientDAO.deleteById(currentClient.getUsername());
            } catch (KeyNotFoundException e) {
                //this case should'nt happen
                e.printStackTrace();
            }

            // invalidation of the session
            session.invalidate();

            // go home
            response.sendRedirect(request.getContextPath());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get Client information to show on profile page
        Client client = (Client) request.getSession(false).getAttribute("client-session");
        request.setAttribute("username", client.getUsername());
        request.setAttribute("email", client.getEmail());

        List<Order> orders;
        Map<Integer, String> productNames;

        // retrieving client orders and product names
        try {
            orders = orderDAO.getClientOrders(client.getUsername());
            if(orders != null) {
                request.setAttribute("orders", orders);
                productNames = new HashMap<>();

                for (Order o : orders) {
                    for(OrderLine ol : o.getOrderLines()) {
                        String pName = productDAO.findById(ol.getProductId()).getName();
                        productNames.put(ol.getProductId(), pName);
                    }
                }
                request.setAttribute("productNames", productNames);
            }
        } catch (KeyNotFoundException e) {
            // not supposed to go here
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
    }
}
