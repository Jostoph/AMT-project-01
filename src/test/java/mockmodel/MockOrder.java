package mockmodel;

import model.Order;
import model.OrderLine;

import java.sql.Date;
import java.util.ArrayList;

public class MockOrder extends Order {
    public MockOrder() {
        super(1, "bob", new Date(42), new ArrayList<>());
        this.getOrderLines().add(new MockOrderLine());
        this.getOrderLines().add(new MockOrderLine());
    }
}
