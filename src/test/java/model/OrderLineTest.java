package model;

import mockmodel.MockProduct;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderLineTest {

    private OrderLine ol;
    private final int quantity = 3;

    @Before
    public void setUp() {
        ol = new OrderLine(quantity,  new MockProduct());
    }

    @Test
    public void getQuantityTest() {
        assertEquals(quantity, ol.getQuantity());
    }

    @Test
    public void getProductTest() {
        assertEquals(1, ol.getProduct().getId());
    }
}