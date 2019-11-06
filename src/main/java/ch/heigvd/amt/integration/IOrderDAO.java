package ch.heigvd.amt.integration;

import ch.heigvd.amt.model.Order;

import javax.ejb.Local;

@Local
public interface IOrderDAO extends IDAO<Integer, Order> {
}
