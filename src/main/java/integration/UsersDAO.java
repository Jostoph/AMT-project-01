package integration;

import business.IAuthenticationService;
import datastore.exceptions.DuplicateKeyException;
import datastore.exceptions.KeyNotFoundException;
import model.User;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Stateless
public class UsersDAO implements IUsersDAO {

    // For Wildfly (see https://docs.jboss.org/author/display/WFLY10/JNDI+Reference)
    @Resource(lookup = "java:/jdbc/shop")
    DataSource dataSource;

    @EJB
    IAuthenticationService authenticationService;

    @Override
    public User create(User entity) throws DuplicateKeyException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO user (USERNAME, EMAIL, HASHED_PW) VALUES (?, ?, ?)");
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getEmail());
            statement.setString(3, authenticationService.hashPassword(entity.getPassword()));
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
    public User findById(String username) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT USERNAME, EMAIL ,USER_ID  FROM user WHERE USERNAME = ?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            boolean hasRecord = rs.next();
            if (!hasRecord) {
                throw new KeyNotFoundException("Could not find user with username = " + username);
            }
            User existingUser = User.builder()
                    .username(rs.getString(1))
                    .email(rs.getString(2))
                    .id(rs.getInt(3))
                    .build();
            return existingUser;
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
            PreparedStatement statement = con.prepareStatement("DELETE FROM user WHERE USERNAME = ?");
            statement.setString(1, username);
            int numberOfDeletedUsers = statement.executeUpdate();
            if (numberOfDeletedUsers != 1) {
                throw new KeyNotFoundException("Could not find user with username = " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public void update(User entity) throws KeyNotFoundException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("UPDATE user SET USERNAME, EMAIL=? WHERE USERNAME = ?");
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getUsername());
            int numberOfUpdatedUsers = statement.executeUpdate();
            if (numberOfUpdatedUsers != 1) {
                throw new KeyNotFoundException("Could not find user with username = " + entity.getUsername());
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