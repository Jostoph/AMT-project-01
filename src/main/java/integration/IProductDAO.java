package integration;

import model.Product;

import java.util.List;

public interface IProductDAO extends IDAO<Integer, Product> {

    List<Product> getChunk(int from, int limit);
}
