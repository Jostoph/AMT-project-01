package model;

import mockmodel.MockOrder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserTest {

    private User user;
    private int id = 1;
    private String name = "Bob";
    private String email = "bob@mail.com";
    private String pwHash = "1234";

    @Before
    public void setUp() {
        user = new User(id, name, email, pwHash, new ArrayList<Order>());
        user.getOrders().add(new MockOrder());
    }

    @Test
    public void getIdTest() {
        assertEquals(id, user.getId());
    }

    @Test
    public void getUsernameTest() {
        assertEquals(name, user.getUsername());
    }

    @Test
    public void getEmailTest() {
        assertEquals(email, user.getEmail());
    }

    @Test
    public void getPwHashTest() {
        assertEquals(pwHash, user.getPwHash());
    }

    @Test
    public void getOrdersTest() {
        assertNotNull(user.getOrders());
        assertEquals(1, user.getOrders().size());
    }

    @Test
    public void setUsernameTest() {
        String newName = "newName";
        user.setUsername(newName);
        assertEquals(newName, user.getUsername());
    }

    @Test
    public void setEmailTest() {
        String newMail = "newmail@mail.com";
        user.setEmail(newMail);
        assertEquals(newMail, user.getEmail());
    }

    @Test
    public void setPwHashTest() {
        String newPw = "5678";
        user.setPwHash(newPw);
        assertEquals(newPw, user.getPwHash());
    }

    @Test
    public void addOrderTest() {
        MockOrder mockOrder = new MockOrder();
        user.getOrders().add(mockOrder);
        assertEquals(2, user.getOrders().size());

    }

    @Test
    public void deleteOrderTest() {
        user.getOrders().remove(0);
        assertEquals(0, user.getOrders().size());
    }
}