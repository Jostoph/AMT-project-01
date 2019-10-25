package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    private Product p;
    private final int id = 1;
    private final String name = "prodName";
    private final double price = 2.5;
    private final String origin = "Mars";
    private final String des = "Some nice product from Mars.";

    @Before
    public void setUp() {
        p = new Product(id, name, price, origin, des);
    }

    @Test
    public void getIdTest() {
        assertEquals(id, p.getId());
    }

    @Test
    public void getNameTest() {
        assertEquals(name, p.getName());
    }

    @Test
    public void getPriceTest() {
        assertEquals(price, p.getPrice(), 0.0);
    }

    @Test
    public void getOriginTest() {
        assertEquals(origin, p.getOrigin());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals(des, p.getDescription());
    }
}