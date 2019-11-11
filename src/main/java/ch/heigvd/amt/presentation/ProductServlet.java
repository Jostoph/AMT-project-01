package ch.heigvd.amt.presentation;

import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.integration.IProductDAO;
import ch.heigvd.amt.model.Product;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/shop/product")
public class ProductServlet extends HttpServlet {

    @EJB
    IProductDAO productDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get the product id from the request
        String productId = request.getParameter("product_id");
        try {
            Integer id = Integer.valueOf(productId);
            Product product;

            // look for the product in the db
            product = productDAO.findById(id);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);

        } catch (KeyNotFoundException | NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/shop/products");
        }
    }
}
