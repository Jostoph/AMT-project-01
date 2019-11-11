package ch.heigvd.amt.integration;

import ch.heigvd.amt.datastore.exceptions.DuplicateKeyException;
import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.model.Product;
import org.arquillian.container.chameleon.deployment.api.DeploymentParameters;
import org.arquillian.container.chameleon.deployment.maven.MavenBuild;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@MavenBuild
@DeploymentParameters(testable = true)
public class ProductDAOTest {

    @EJB
    IProductDAO productDAO;

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateAProduct() throws DuplicateKeyException {
        Product product = new Product(-1, "product", 4.2, "earth", "some product");
        productDAO.create(product);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateAndRetrieveAProduct() throws DuplicateKeyException, KeyNotFoundException {
        Product product = new Product(-1, "product", 4.2, "earth", "some product");
        Product createdProduct = productDAO.create(product);
        Product loadedProduct = productDAO.findById(createdProduct.getId());

        assertEquals(product, createdProduct);
        assertSame(product, createdProduct);
        assertNotSame(product, loadedProduct);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToDeleteAProduct() throws DuplicateKeyException, KeyNotFoundException {
        Product product = new Product(-1, "product", 4.2, "earth", "some product");
        Product createdProduct = productDAO.create(product);
        Product loadedProduct = productDAO.findById(createdProduct.getId());

        assertEquals(product, createdProduct);
        productDAO.deleteById(createdProduct.getId());

        boolean hasThrown = false;
        try {
            productDAO.findById(createdProduct.getId());
        } catch (KeyNotFoundException e) {
            hasThrown = true;
        }
        assertTrue(hasThrown);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToUpdateAProduct() throws DuplicateKeyException, KeyNotFoundException {
        Product product = new Product(-1, "product", 4.2, "earth", "some product");
        Product createdProduct = productDAO.create(product);
        Product modifiedProduct = new Product(createdProduct.getId(), "othername", 3.6, "mars", "hello world");
        productDAO.update(modifiedProduct);
        Product loadedModifiedProcut = productDAO.findById(createdProduct.getId());

        assertEquals(modifiedProduct, loadedModifiedProcut);
        assertNotEquals(createdProduct, loadedModifiedProcut);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToGetAProductChunk() throws DuplicateKeyException, KeyNotFoundException {
        Product p1 = new Product(-1, "p1", 1.0, "a", "a");
        Product p2 = new Product(-1, "p2", 2.0, "b", "b");
        Product p3 = new Product(-1, "p3", 3.0, "c", "c");

        Product createdP1 = productDAO.create(p1);
        Product createdP2 = productDAO.create(p2);
        Product createdP3 = productDAO.create(p3);

        List<Product> loadedProducts = productDAO.getChunk(1, 2);

        assertEquals(2, loadedProducts.size());

        assertEquals(createdP2, loadedProducts.get(0));
        assertEquals(createdP3, loadedProducts.get(1));
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToGetTheNumberOfProducts() throws DuplicateKeyException, KeyNotFoundException {
        Product p1 = new Product(-1, "p1", 1.0, "a", "a");
        Product p2 = new Product(-1, "p2", 2.0, "b", "b");
        Product p3 = new Product(-1, "p3", 3.0, "c", "c");

        productDAO.create(p1);
        productDAO.create(p2);
        productDAO.create(p3);

        int noProducts = productDAO.getNoRecords();
        assertEquals(3, noProducts);
    }
}


