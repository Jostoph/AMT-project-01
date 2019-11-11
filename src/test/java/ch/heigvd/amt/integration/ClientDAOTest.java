package ch.heigvd.amt.integration;

import ch.heigvd.amt.datastore.exceptions.DuplicateKeyException;
import ch.heigvd.amt.model.Client;
import org.arquillian.container.chameleon.deployment.api.DeploymentParameters;
import org.arquillian.container.chameleon.deployment.maven.MavenBuild;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

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
}
