package ch.heigvd.amt.integration;

import ch.heigvd.amt.datastore.exceptions.DuplicateKeyException;
import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.model.Client;
import ch.heigvd.amt.model.Order;
import ch.heigvd.amt.model.OrderLine;
import ch.heigvd.amt.model.Product;
import org.arquillian.container.chameleon.deployment.api.DeploymentParameters;
import org.arquillian.container.chameleon.deployment.maven.MavenBuild;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@MavenBuild
@DeploymentParameters(testable = true)
public class OrderDAOTest {

    @EJB
    IOrderDAO orderDAO;

    @EJB
    IClientDAO clientDAO;

    @EJB
    IProductDAO productDAO;

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateAnOrder() throws DuplicateKeyException {
        Client client = new Client("Bob", "bob@mail.com", "password");
        Product product = new Product(-1, "prodname", 4.2, "earth", "some product");

        clientDAO.create(client);
        Product createdProduct = productDAO.create(product);

        OrderLine orderLine = new OrderLine(3, createdProduct.getId());
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);
        Order order = new Order(-1, "Bob", new Date(1), orderLines);

        orderDAO.create(order);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateAndRetrieveAnOrder() throws DuplicateKeyException, KeyNotFoundException {
        Client client = new Client("Bob", "bob@mail.com", "password");
        Product product = new Product(1, "prodname", 4.2, "earth", "some product");

        clientDAO.create(client);
        Product createdProduct = productDAO.create(product);

        OrderLine orderLine = new OrderLine(3, createdProduct.getId());
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);
        Order order = new Order(-1, "Bob", new Date(1), orderLines);

        Order createdOrder = orderDAO.create(order);
        Order loadedOrder = orderDAO.findById(createdOrder.getId());

        assertEquals(order, createdOrder);
        assertSame(order, createdOrder);
        assertNotSame(order, loadedOrder);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToDeleteAnOrder() throws DuplicateKeyException, KeyNotFoundException {
        Client client = new Client("Bob", "bob@mail.com", "password");
        Product product = new Product(1, "prodname", 4.2, "earth", "some product");

        clientDAO.create(client);
        Product createdProduct = productDAO.create(product);

        OrderLine orderLine = new OrderLine(3, createdProduct.getId());
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);
        Order order = new Order(-1, "Bob", new Date(1), orderLines);

        Order createdOrder = orderDAO.create(order);
        Order loadedOrder = orderDAO.findById(createdOrder.getId());

        assertEquals(order, createdOrder);

        orderDAO.deleteById(createdOrder.getId());

        boolean hasThrown = false;
        try {
            loadedOrder = orderDAO.findById(createdOrder.getId());
        } catch (KeyNotFoundException e) {
            hasThrown = true;
        }

        assertTrue(hasThrown);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldNotBePossibleToUpdateAnOrder() throws DuplicateKeyException, UnsupportedOperationException {
        Client client = new Client("Bob", "bob@mail.com", "password");
        Product product = new Product(1, "prodname", 4.2, "earth", "some product");

        clientDAO.create(client);
        Product createdProduct = productDAO.create(product);

        OrderLine orderLine = new OrderLine(3, createdProduct.getId());
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);
        Order order = new Order(-1, "Bob", new Date(1), orderLines);

        Order createdOrder = orderDAO.create(order);
        Order modifiedOrder = new Order(-1 , "Bob", new Date(2), orderLines);

        boolean hasThrown = false;
        try {
            orderDAO.update(modifiedOrder);
        } catch (Exception e) {
            hasThrown = true;
        }

        assertTrue(hasThrown);
    }
}

