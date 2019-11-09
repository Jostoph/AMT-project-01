package ch.heigvd.amt.presentation;

import ch.heigvd.amt.integration.IOrderDAO;
import ch.heigvd.amt.model.Client;
import ch.heigvd.amt.model.Order;
import ch.heigvd.amt.model.OrderLine;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderTestServlet", urlPatterns = "shop/order")
public class OrderTestServlet extends HttpServlet {

    @EJB
    IOrderDAO orderDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderLine> orders = new ArrayList<>();
        orders.add(new OrderLine(3, 1));
        orders.add(new OrderLine(4, 2));
        Order order = new Order(-1, ((Client) req.getSession(false).getAttribute("client-session")).getUsername(), null, orders);
        try {
            orderDAO.create(order);
            resp.getWriter().println("create order");
        } catch (Exception e) {
            resp.getWriter().println("Could not create order : " + e.getMessage());
        }
    }
}
