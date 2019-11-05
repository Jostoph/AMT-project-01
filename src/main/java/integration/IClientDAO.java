package integration;

import model.Client;

import javax.ejb.Local;

@Local
public interface IClientDAO extends IDAO<String, Client> {

}
