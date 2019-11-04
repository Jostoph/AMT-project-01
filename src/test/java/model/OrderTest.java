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
    private final Date date = new Date(42);
    private List<OrderLine> orderList = new ArrayList<OrderLine>();


    @Before
    public void setUp() {
        orderList.add(new MockOrderLine());
        orderList.add(new MockOrderLine());
        order = new Order(id, date, orderList);
    }

    @Test
    public void getNumTest() {
        assertEquals(id, order.getId());
    }

    @Test
    public void getDateTest() {
        assertEquals(date.toString(), order.getDate().toString());
    }

    @Test
    public void getOrderListTest() {
        assertNotNull(order.getOrderList());
        assertEquals(2, order.getOrderList().size());
    }
}