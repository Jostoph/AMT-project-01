package ch.heigvd.amt.model;

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

    @Test
    public void setIdTest() {
        p.setId(42);
        assertEquals(42, p.getId());
    }

    @Test
    public void setNameTest() {
        p.setName("newProdName");
        assertEquals("newProdName", p.getName());
    }

    @Test
    public void setPriceTest() {
        p.setPrice(4.2);
        assertEquals(4.2, p.getPrice(), 0);
    }

    @Test
    public void setOriginTest() {
        p.setOrigin("newOrigin");
        assertEquals("newOrigin", p.getOrigin());
    }

    @Test
    public void setDescriptionTest() {
        p.setDescription("newDescription");
        assertEquals("newDescription", p.getDescription());
    }
}