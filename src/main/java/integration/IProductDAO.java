package integration;

import model.Product;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IProductDAO extends IDAO<Integer, Product> {
    List<Product> getChunk(int from, int limit);
}
