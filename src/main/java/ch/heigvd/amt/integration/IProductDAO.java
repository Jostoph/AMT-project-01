package ch.heigvd.amt.integration;

import ch.heigvd.amt.model.Product;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IProductDAO extends IDAO<Integer, Product> {

    /**
     *
     * @param from starting index
     * @param limit number of records to retrieve
     * @return a chunk (pagination) of products
     */
    List<Product> getChunk(int from, int limit);

    /**
     * @return the number of products
     */
    int getNoRecords();
}
