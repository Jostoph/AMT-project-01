package ch.heigvd.amt.integration;

import ch.heigvd.amt.datastore.exceptions.DuplicateKeyException;
import ch.heigvd.amt.datastore.exceptions.KeyNotFoundException;
import ch.heigvd.amt.model.Client;
import org.arquillian.container.chameleon.deployment.api.DeploymentParameters;
import org.arquillian.container.chameleon.deployment.maven.MavenBuild;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@MavenBuild
@DeploymentParameters(testable = true)
public class ClientDAOTest {

    @EJB
    IClientDAO clientDAO;

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateAClient() throws DuplicateKeyException {
        Client client = new Client("AAAA", "alice@mail.com", "password");
        clientDAO.create(client);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateAndRetrieveAClient() throws DuplicateKeyException, KeyNotFoundException {
        Client client = new Client("AAAA", "alice@mail.com", "password");
        Client createdClient = clientDAO.create(client);
        Client loadedClient = clientDAO.findById(createdClient.getUsername());

        assertEquals(client, createdClient);
        assertEquals(client, loadedClient);
        assertSame(client, createdClient);
        assertNotSame(client, loadedClient);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToDeleteAClient() throws DuplicateKeyException, KeyNotFoundException {
        Client client = new Client("Bob", "bob@mail.com", "password");
        Client createdClient = clientDAO.create(client);
        Client loadedClient = clientDAO.findById(createdClient.getUsername());

        assertEquals(client, createdClient);
        clientDAO.deleteById(createdClient.getUsername());

        boolean hasThrown = false;
        try {
            loadedClient = clientDAO.findById(createdClient.getUsername());
        } catch (KeyNotFoundException e) {
            hasThrown = true;
        }

        assertTrue(hasThrown);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToUpdateAClient() throws DuplicateKeyException, KeyNotFoundException {
        Client client = new Client("Bobby", "bobby@mail.com", "password");
        Client createdClient = clientDAO.create(client);
        Client modifiedClient = new Client(client.getUsername(), "newBobby@mail.com", "newPassword");
        clientDAO.update(modifiedClient);

        Client loadedModifiedClient = clientDAO.findById(client.getUsername());

        assertEquals(modifiedClient, loadedModifiedClient);
        assertNotEquals(createdClient, loadedModifiedClient);
    }
}
