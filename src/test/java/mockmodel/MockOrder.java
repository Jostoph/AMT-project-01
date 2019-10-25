package mockmodel;

import model.Order;
import model.OrderLine;

import java.sql.Date;
import java.util.ArrayList;

public class MockOrder extends Order {
    public MockOrder() {
        super(1, new Date(42), new ArrayList<OrderLine>());
        this.getOrderList().add(new MockOrderLine());
        this.getOrderList().add(new MockOrderLine());
    }
}
