package ch.heigvd.amt.presentation;

import ch.heigvd.amt.integration.IProductDAO;
import ch.heigvd.amt.model.Client;
import ch.heigvd.amt.model.Order;
import ch.heigvd.amt.model.OrderLine;
import ch.heigvd.amt.model.Product;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductsServlet", urlPatterns = {"/shop/products", "/shop/*"})
public class ProductsServlet extends HttpServlet {

    @EJB
    IProductDAO productDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int productId;
        int quantity;

        try {
            productId = Integer.valueOf(request.getParameter("product_id"));
            quantity = Integer.valueOf(request.getParameter("quantity"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath());
            return;
        }

        HttpSession session = request.getSession(false);

        Order order = (Order) session.getAttribute("order");

        if(order != null) {
            order.getOrderLines().add(new OrderLine(quantity, productId));
            response.sendRedirect(request.getContextPath() + "/shop/products?pageNum=" + request.getParameter("currentPage"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNum = 1;
        int chunkSize = 10;


        HttpSession session = request.getSession(false);
        Order currentOrder = (Order) session.getAttribute("order");

        Client currentClient = (Client) session.getAttribute("client-session");

        if(currentOrder == null) {
            List<OrderLine> orderLines = new ArrayList<>();
            session.setAttribute("order", new Order(-1, currentClient.getUsername(), null, orderLines));
        }

        String reqPage = request.getParameter("pageNum");

        if(reqPage != null) {
            try {
                pageNum = Integer.valueOf(reqPage);
            } catch (NumberFormatException e) {
                // do nothing
            }
        }

        int totalNum = productDAO.getNoRecords();

        if(totalNum > 0) {
            List<Product> products = productDAO.getChunk((pageNum - 1) * chunkSize, chunkSize);

            request.setAttribute("products", products);
            request.setAttribute("pageNum", pageNum);
            request.setAttribute("totalNum", totalNum);
        }

        request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request, response);
    }
}
