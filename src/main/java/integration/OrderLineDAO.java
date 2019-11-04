package integration;

import business.IAuthenticationService;
import datastore.exceptions.DuplicateKeyException;
import datastore.exceptions.KeyNotFoundException;
import model.Order;
import model.OrderLine;
import model.User;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderLineDAO implements IOrderLineDAO {
    // For Wildfly (see https://docs.jboss.org/author/display/WFLY10/JNDI+Reference)
    @Resource(lookup = "java:/jdbc/shop")
    DataSource dataSource;

    @Override
    public OrderLine create(OrderLine orderLine) throws DuplicateKeyException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO orderLine  (PRODUCT_ID , ORDER_ID, QUANTITY) VALUES (?, ?, ?)");
            statement.setInt(1, orderLine.getProduct_ID());
            statement.setInt(2, orderLine.getOrderId());
            statement.setInt(3,orderLine.getQuantity());
            statement.execute();
            return orderLine;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public OrderLine findById(Integer id) throws KeyNotFoundException {
//        Connection con = null;
//        try {
//            con = dataSource.getConnection();
//            PreparedStatement statement = con.prepareStatement("SELECT PRODUCT_ID , ORDER_ID, QUANTITY FROM orderLine  WHERE PRODUCT_ID = ? AND ORDER_ID = ?  ");
//            statement.setInt(1, );
//            ResultSet rs = statement.executeQuery();
//            boolean hasRecord = rs.next();
//            if (!hasRecord) {
//                throw new KeyNotFoundException("Could not find user with username = " + username);
//            }
//            User existingUser = User.builder()
//                    .username(rs.getString(1))
//                    .email(rs.getString(2))
//                    .id(rs.getInt(3))
//                    .build();
//            return existingUser;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new Error(e);
//        } finally {
//            closeConnection(con);
//        }
        return null;
    }

    @Override
    public void update(OrderLine entity) throws KeyNotFoundException {

    }

    @Override
    public void deleteById(Integer id) throws KeyNotFoundException {

    }


    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
