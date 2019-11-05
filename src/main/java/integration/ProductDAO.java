package integration;

import datastore.exceptions.DuplicateKeyException;
import datastore.exceptions.KeyNotFoundException;
import model.Product;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Local
public class ProductDAO implements IProductDAO {

    @Resource(lookup = "java:/jdbc/shop")
    DataSource dataSource;

    @Override
    public Product create(Product entity) throws DuplicateKeyException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO products (PRODUCT_NAME, PRICE, ORIGIN, DESCRIPTION) VALUES (?, ?, ?, ?)");
            statement.setString(1, entity.getName());
            statement.setDouble(2, entity.getPrice());
            statement.setString(3, entity.getOrigin());
            statement.setString(4, entity.getDescription());
            statement.execute();
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public Product findById(Integer id) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT PRODUCT_NAME, PRICE, ORIGIN, DESCRIPTION FROM clients WHERE PRODUCT_ID = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            boolean hasRecord = rs.next();

            if(!hasRecord) {
                throw new KeyNotFoundException("Could not find product with id : " + id);
            }

            return new Product(id, rs.getString(1), rs.getDouble(2), rs.getString(3), rs.getString(4));
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public void update(Product entity) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("UPDATE products SET PRODUCT_NAME, PRICE, ORIGIN, DESCRIPTION WHERE PRODUCT_ID = ?");
            statement.setString(1, entity.getName());
            statement.setDouble(2, entity.getPrice());
            statement.setString(3, entity.getOrigin());
            statement.setString(4, entity.getDescription());

            int numberOfUpdatedProducts = statement.executeUpdate();
            if(numberOfUpdatedProducts != 1) {
                throw new KeyNotFoundException("Could not find product with id : " + entity.getId());
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

    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
