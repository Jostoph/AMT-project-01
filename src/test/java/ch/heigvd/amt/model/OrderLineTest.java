package ch.heigvd.amt.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderLineTest {

    private OrderLine ol;
    private final int quantity = 3;
    private final int productId = 4;

    @Before
    public void setUp() {
        ol = new OrderLine(quantity, productId );
    }

    @Test
    public void getQuantityTest() {
        assertEquals(quantity, ol.getQuantity());
    }

    @Test
    public void getProductTest() {
        assertEquals(productId, ol.getProductId());
    }

    @Test
    public void setQuantityTest() {
        ol.setQuantity(42);
        assertEquals(42, ol.getQuantity());
    }

    @Test
    public void setProductIdTest() {
        ol.setProductId(42);
        assertEquals(42, ol.getProductId());
    }
}