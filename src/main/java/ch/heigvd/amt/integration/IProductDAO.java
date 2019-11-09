package ch.heigvd.amt.integration;

import ch.heigvd.amt.model.Product;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IProductDAO extends IDAO<Integer, Product> {

    List<Product> getChunk(int from, int limit);

    int getNoRecords();
}
