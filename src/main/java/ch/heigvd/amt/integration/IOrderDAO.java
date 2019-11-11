package ch.heigvd.amt.integration;

import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.model.Order;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IOrderDAO extends IDAO<Integer, Order> {

    List<Order> getClientOrders(String username) throws KeyNotFoundException;

    @Override
    void update(Order entity) throws UnsupportedOperationException;
}
