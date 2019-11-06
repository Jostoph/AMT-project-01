package ch.heigvd.amt.integration;

import ch.heigvd.amt.model.Client;

import javax.ejb.Local;

@Local
public interface IClientDAO extends IDAO<String, Client> {
}
