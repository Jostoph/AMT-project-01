package integration;

import business.IAuthenticationService;
import datastore.exceptions.DuplicateKeyException;
import datastore.exceptions.KeyNotFoundException;
import model.Order;
import model.User;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO implements IOrderDAO {

    // For Wildfly (see https://docs.jboss.org/author/display/WFLY10/JNDI+Reference)
    @Resource(lookup = "java:/jdbc/shop")
    DataSource dataSource;

    @EJB
    IAuthenticationService authenticationService;

    @Override
    public Order create(Order order) throws DuplicateKeyException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO clientOrder (USER_ID,DATE) VALUES (?,?)");
            statement.setInt(1, order.getUserId());
            statement.setDate(2, order.getDate());
            statement.execute();
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }


    @Override
    public Order findById(Integer id) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT ORDER_ID,USER_ID,DATE  FROM clientOrder WHERE ORDER_ID = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            boolean hasRecord = rs.next();
            if (!hasRecord) {
                throw new KeyNotFoundException("Could not find order with id = " + id);
            }
            Order existingOrder = Order.builder()
                    .id(rs.getInt(1))
                    .userId(rs.getInt(2))
                    .date(rs.getDate(3))
                    .build();
            return existingOrder;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public void update(Order order) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("UPDATE user SET ORDER_ID ,USER_ID =? , DATE =? WHERE ORDER_ID = ?");
            statement.setInt(1, order.getUserId());
            statement.setDate(2, order.getDate());
            int numberOfUpdatedUsers = statement.executeUpdate();
            if (numberOfUpdatedUsers != 1) {
                throw new KeyNotFoundException("Could not find order with order id = " + order.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public void deleteById(Integer id) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("DELETE FROM clientOrder  WHERE ORDER_ID = ?");
            statement.setInt(1, id);
            int numberOfDeletedUsers = statement.executeUpdate();
            if (numberOfDeletedUsers != 1) {
                throw new KeyNotFoundException("Could not find order with order id  = " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
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
