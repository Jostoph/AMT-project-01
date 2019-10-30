package integration;

import model.User;

import javax.ejb.Local;

@Local
public interface IUsersDAO extends IDAO<String, User> {

}