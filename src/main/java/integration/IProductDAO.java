package integration;

import model.Product;

import javax.ejb.Local;

@Local
public interface IProductDAO extends IDAO<Integer, Product> {
}
