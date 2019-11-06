package ch.heigvd.amt.mockmodel;

import ch.heigvd.amt.model.Order;
import ch.heigvd.amt.model.OrderLine;

import java.sql.Date;
import java.util.ArrayList;

public class MockOrder extends Order {
    public MockOrder() {
        super(1, "bob", new Date(42), new ArrayList<OrderLine>());
        this.getOrderLines().add(new MockOrderLine());
        this.getOrderLines().add(new MockOrderLine());
    }
}
