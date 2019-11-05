package integration;

import datastore.exceptions.DuplicateKeyException;
import datastore.exceptions.KeyNotFoundException;
import model.Product;

public class ProductDAO implements IProductDAO {
    @Override
    public Product create(Product entity) throws DuplicateKeyException {
        return null;
    }

    @Override
    public Product findById(Integer id) throws KeyNotFoundException {
        return null;
    }

    @Override
    public void update(Product entity) throws KeyNotFoundException {

    }

    @Override
    public void deleteById(Integer id) throws KeyNotFoundException {

    }
}
