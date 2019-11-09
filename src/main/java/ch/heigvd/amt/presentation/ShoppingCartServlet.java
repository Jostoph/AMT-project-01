package ch.heigvd.amt.presentation;

import ch.heigvd.amt.datastore.exceptions.DuplicateKeyException;
import ch.heigvd.amt.integration.IOrderDAO;
import ch.heigvd.amt.model.Order;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ShoppingCartServlet", urlPatterns = "/shop/cart")
public class ShoppingCartServlet extends HttpServlet {

    @EJB
    IOrderDAO orderDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO make the actual order validation from here
        // TODO add button to see cart -> replace github
        // TODO format JSP...
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO this is a test implementation !! need to display page with actual order that must be validated
        HttpSession session = request.getSession(false);

        Order currentOrder = (Order) session.getAttribute("order");

        if(currentOrder == null) {
            System.out.println("Order is null");
        } else {
            if(currentOrder.getOrderLines().isEmpty()) {
                System.out.println("Order line is empty");
            } else {
                try {
                    orderDAO.create(currentOrder);
                    session.invalidate();
                    System.out.println("order created");
                } catch (DuplicateKeyException e) {
                    System.out.println("error in order creation (duplicate)");
                }
            }
        }
    }
}
