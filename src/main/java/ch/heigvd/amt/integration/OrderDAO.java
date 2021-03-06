package ch.heigvd.amt.integration;

import ch.heigvd.amt.datastore.exceptions.DuplicateKeyException;
import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.model.Order;
import ch.heigvd.amt.model.OrderLine;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class OrderDAO implements IOrderDAO {

    @Resource(lookup = "java:/jdbc/shop")
    DataSource dataSource;

    @Override
    public Order create(Order entity) throws DuplicateKeyException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO clientOrders (USERNAME) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getUsername());

            int mordifiedRows = statement.executeUpdate();

            if(mordifiedRows != 1) {
                throw new SQLException("Create order failed, no rows where changed.");
            }

            // get the generated key
            ResultSet key = statement.getGeneratedKeys();

            if(key.next()) {
                entity.setId(key.getInt(1));

                // create orderLines for the Order
                createOrderLines(entity, con);
            }

            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public Order findById(Integer id) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT USERNAME, ORDER_DATE FROM clientOrders WHERE ORDER_ID = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            boolean hasRecord = rs.next();

            if(!hasRecord) {
                throw new KeyNotFoundException("Could not find order with id : " + id);
            }

            // retrieve the orderLines of the order
            List<OrderLine> orderLines = findOrderLinesById(id, con);

            return new Order(id, rs.getString(1), rs.getDate(2), orderLines);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    // not used, we choose to make our orders immutable in the app
    public void update(Order entity) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("An Order can't be changed");
    }

    @Override
    public void deleteById(Integer id) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("DELETE FROM clientOrders WHERE ORDER_ID = ?");
            statement.setInt(1, id);

            int numberOfDeletedOrders = statement.executeUpdate();

            if(numberOfDeletedOrders != 1) {
                throw new KeyNotFoundException("Could not find order with id : " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public List<Order> getClientOrders(String username) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT ORDER_ID FROM clientOrders WHERE USERNAME = ?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            List<Order> orders = new ArrayList<>();

            // get all the orders of the client
            while (rs.next()) {
                orders.add(findById(rs.getInt(1)));
            }

            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    private void createOrderLines(Order entity, Connection con) throws DuplicateKeyException {
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO orderLines (PRODUCT_ID, ORDER_ID, QUANTITY) VALUES (?, ?, ?)");
            for(OrderLine ol : entity.getOrderLines()) {
                statement.setInt(1, ol.getProductId());
                statement.setInt(2, entity.getId());
                statement.setInt(3, ol.getQuantity());
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    private List<OrderLine> findOrderLinesById(int orderId, Connection con) throws KeyNotFoundException {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT PRODUCT_ID, QUANTITY FROM orderLines WHERE ORDER_ID = ?");
            ArrayList<OrderLine> retrievedOrderLines = new ArrayList<>();
            statement.setInt(1, orderId);
            ResultSet rs = statement.executeQuery();

            // get all the orderLines from an order
            while (rs.next()) {
                retrievedOrderLines.add(new OrderLine(rs.getInt(2), rs.getInt(1)));
            }
            return retrievedOrderLines;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
