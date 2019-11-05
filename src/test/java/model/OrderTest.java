package model;

import mockmodel.MockOrderLine;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderTest {

    private Order order;
    private final int id = 1;
    private final String username = "bob";
    private ArrayList<OrderLine> orderLines;
    private final Date date = new Date(42);


    @Before
    public void setUp() {
        orderLines = new ArrayList<>();
        orderLines.add(new MockOrderLine());
        orderLines.add(new MockOrderLine());
        order = new Order(id, username, date, orderLines);
    }

    @Test
    public void getIdTest() {
        assertEquals(id, order.getId());
    }

    @Test
    public void getDateTest() {
        assertEquals(date.toString(), order.getDate().toString());
    }

    @Test
    public void getUsername() {
        assertEquals(username, order.getUsername());
    }

    @Test
    public void getOrderLines() {
        assertEquals(orderLines, order.getOrderLines());
    }

    @Test
    public void setId() {
        int newId = 42;
        order.setId(newId);
        assertEquals(newId, order.getId());
    }

    @Test
    public void setDate() {
        Date newDate = new Date(20);
        order.setDate(newDate);
        assertEquals(newDate.toString(), order.getDate().toString());
    }

    @Test
    public void setUsername() {
        String newUsername = "alice";
        order.setUsername(newUsername);
        assertEquals(newUsername, order.getUsername());
    }

    @Test
    public void setOrderLines() {
        ArrayList<OrderLine> newOrderLines = new ArrayList<>();
        newOrderLines.add(new MockOrderLine());
        order.setOrderLines(newOrderLines);
        assertEquals(newOrderLines, order.getOrderLines());
    }
}