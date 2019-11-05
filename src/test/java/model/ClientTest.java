package model;

import mockmodel.MockOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ClientTest {

    private Client client;
    private String name = "Bob";
    private String email = "bob@mail.com";
    private String password = "1234";

    @Before
    public void setUp() {
        client = new Client(name, email, password);
    }

    @Test
    public void getUsernameTest() {
        assertEquals(name, client.getUsername());
    }

    @Test
    public void getEmailTest() {
        assertEquals(email, client.getEmail());
    }

    @Test
    public void getPassword() {
        assertEquals(password, client.getPassword());
    }

    @Test
    public void setUsernameTest() {
        String newName = "newName";
        client.setUsername(newName);
        assertEquals(newName, client.getUsername());
    }

    @Test
    public void setEmailTest() {
        String newMail = "newmail@mail.com";
        client.setEmail(newMail);
        assertEquals(newMail, client.getEmail());
    }

    @Test
    public void setPasswordTest() {
        String newPw = "5678";
        client.setPassword(newPw);
        assertEquals(newPw, client.getPassword());
    }
}