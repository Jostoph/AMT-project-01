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

        HttpSession session = request.getSession(false);

        Order currentOrder = (Order) session.getAttribute("order");

        if(request.getParameter("command") != null) {
            // send order
            if(currentOrder != null && !currentOrder.getOrderLines().isEmpty()) {
                try {
                    orderDAO.create(currentOrder);
                    // reset order
                    session.removeAttribute("order");
                    response.sendRedirect(request.getContextPath() + "/shop/profile");
                } catch (DuplicateKeyException e) {
                    e.printStackTrace();
                    response.sendRedirect(request.getContextPath() + "/shop/products");
                }
            } else {
                // should never go there
                response.sendRedirect(request.getContextPath() + "/shop/products");
            }

        } else if(request.getParameter("cancel") != null) {
            // cancel order
            session.removeAttribute("order");
            response.sendRedirect(request.getContextPath() + "/shop/shop");
        } else {
            // should not go there
            request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        Order currentOrder = (Order) session.getAttribute("order");

        if(currentOrder != null) {
            request.setAttribute("order", currentOrder);
            request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
        } else {
            // should never go there
            response.sendRedirect(request.getContextPath() + "/shop/products");
        }
    }
}
