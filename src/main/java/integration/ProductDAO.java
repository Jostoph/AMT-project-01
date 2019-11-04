package integration;

import business.IAuthenticationService;
import datastore.exceptions.DuplicateKeyException;
import datastore.exceptions.KeyNotFoundException;
import model.Product;
import model.User;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO implements IProductDAO {
    // For Wildfly (see https://docs.jboss.org/author/display/WFLY10/JNDI+Reference)
    @Resource(lookup = "java:/jdbc/shop")
    DataSource dataSource;
    @EJB
    IAuthenticationService authenticationService;

    @Override
    public Product create(Product entity) throws DuplicateKeyException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO product  (NAME, PRICE, ORIGIN,DESCRIPTION) VALUES (?, ?, ?, ?)");
            statement.setString(1, entity.getName());
            statement.setFloat(2, entity.getPrice());
            statement.setString(3, entity.getOrigin());
            statement.setString(4,entity.getDescription());
            statement.execute();
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public Product findById(String name) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT NAME, PRICE , ORIGIN,DESCRIPTION FROM product WHERE NAME = ?");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            boolean hasRecord = rs.next();
            if (!hasRecord) {
                throw new KeyNotFoundException("Could not find product with name = " + name);
            }
            Product existingProduct = Product.builder()
                    .name(rs.getString(1))
                    .price(rs.getFloat(2))
                    .origin(rs.getString(3))
                    .description(rs.getString(4))
                    .build();
            return existingProduct;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public void update(Product product) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("UPDATE user SET NAME, PRICE=?,ORIGIN = ? , DESCRIPTION=? WHERE NAME = ?");
            statement.setFloat(1,product.getPrice());
            statement.setString(2, product.getOrigin());
            statement.setString(3,product.getDescription());
            statement.setString(4,product.getName());
            int numberOfUpdatedUsers = statement.executeUpdate();
            if (numberOfUpdatedUsers != 1) {
                throw new KeyNotFoundException("Could not find product with name = " + product.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public void deleteById(String productName) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("DELETE FROM product WHERE name = ?");
            statement.setString(1, productName);
            int numberOfDeletedUsers = statement.executeUpdate();
            if (numberOfDeletedUsers != 1) {
                throw new KeyNotFoundException("Could not find user with username = " + productName);
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
