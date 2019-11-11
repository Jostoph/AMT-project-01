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

        // get client session
        HttpSession session = request.getSession(false);

        // get the Order from the session (the shopping cart)
        Order order = (Order) session.getAttribute("order");

        if(order != null) {
            order.getOrderLines().add(new OrderLine(quantity, productId));
            response.sendRedirect(request.getContextPath() + "/shop/products?pageNum=" + request.getParameter("currentPage"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // default page number
        int pageNum = 1;

        // pagination size (chunk)
        int chunkSize = 10;

        // create an Order in the Client session (used as a shopping cart)
        HttpSession session = request.getSession(false);
        Order currentOrder = (Order) session.getAttribute("order");

        Client currentClient = (Client) session.getAttribute("client-session");

        if(currentOrder == null) {
            List<OrderLine> orderLines = new ArrayList<>();
            session.setAttribute("order", new Order(-1, currentClient.getUsername(), null, orderLines));
        }

        // get the page number
        String reqPage = request.getParameter("pageNum");

        if(reqPage != null) {
            try {
                pageNum = Integer.parseInt(reqPage);
            } catch (NumberFormatException e) {
                // do nothing
            }
        }

        // get the total number of records
        int totalNum = productDAO.getNoRecords();

        if(totalNum > 0) {
            // get the requested products chunk
            List<Product> products = productDAO.getChunk((pageNum - 1) * chunkSize, chunkSize);

            request.setAttribute("products", products);
            request.setAttribute("pageNum", pageNum);
            request.setAttribute("totalNum", totalNum);
        }

        request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request, response);
    }
}
