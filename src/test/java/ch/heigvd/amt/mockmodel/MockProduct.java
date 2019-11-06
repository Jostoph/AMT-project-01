package ch.heigvd.amt.mockmodel;

import ch.heigvd.amt.model.Product;

public class MockProduct extends Product {
    public MockProduct() {
        super(1, "name", 2.5, "Mars", "Some description");
    }

    @Override
    public int getId() {
        return 1;
    }
}
