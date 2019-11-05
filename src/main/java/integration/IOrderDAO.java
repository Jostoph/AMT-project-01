package integration;

import model.Order;

import javax.ejb.Local;

@Local
public interface IOrderDAO extends IDAO<Integer, Order> {
}
