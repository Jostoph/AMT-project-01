package mockmodel;

import model.OrderLine;

public class MockOrderLine extends OrderLine {
    public MockOrderLine() {
        super(3, new MockProduct());
    }
}
