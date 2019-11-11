package ch.heigvd.amt.integration;


import ch.heigvd.amt.buisness.IAuthenticationService;
import ch.heigvd.amt.datastore.exceptions.DuplicateKeyException;
import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.model.Client;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Stateless
public class ClientDAO implements IClientDAO {

    @Resource(lookup = "java:/jdbc/shop")
    DataSource dataSource;

    @Override
    public Client create(Client entity) throws DuplicateKeyException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO clients (USERNAME, EMAIL, PASSWORD_HASH) VALUES (?, ?, ?)");
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());
            statement.execute();

            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new DuplicateKeyException(e.getMessage());
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public Client findById(String username) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT USERNAME, EMAIL, PASSWORD_HASH FROM clients WHERE USERNAME = ?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            boolean hasRecord = rs.next();

            if(!hasRecord) {
                throw new KeyNotFoundException("Could not find client with username : " + username);
            }

            return new Client(rs.getString(1), rs.getString(2), rs.getString(3));

        } catch (SQLException e) {
            e.printStackTrace();
            throw  new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public void update(Client entity) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("UPDATE clients SET EMAIL = ?, PASSWORD_HASH = ? WHERE USERNAME = ?");
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getUsername());
            int numberOfUpdatedClients = statement.executeUpdate();
            if(numberOfUpdatedClients != 1) {
                throw new KeyNotFoundException("Could not find client with username : " + entity.getUsername());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public void deleteById(String username) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("DELETE FROM clients WHERE USERNAME = ?");
            statement.setString(1, username);
            int numberOfDeletedClients = statement.executeUpdate();
            if(numberOfDeletedClients != 1) {
                throw new KeyNotFoundException("Could not find client with username : " + username);
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
