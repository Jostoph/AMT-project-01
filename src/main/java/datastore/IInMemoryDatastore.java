package datastore;

import datastore.exceptions.DuplicateKeyException;
import datastore.exceptions.KeyNotFoundException;
import model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IInMemoryDatastore {

    List<User> getAllUsers();

    void insertUser(User user) throws DuplicateKeyException;

    User loadUserByUsername(String username) throws KeyNotFoundException;

    void updateUser(User user) throws KeyNotFoundException;


}