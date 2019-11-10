package ch.heigvd.amt.buisness;

import org.junit.Test;

import javax.ejb.EJB;

import static org.junit.Assert.*;

public class AuthenticationServiceTest {

    private IAuthenticationService auth = new AuthenticationService();

    @Test
    public void hashPasswordShouldHash() {
        String plainText = "text";
        String hashed = auth.hashPassword(plainText);

        assertNotEquals(plainText, hashed);
    }

    @Test
    public void checkPasswordShouldReturnTrueOnSameText() {
        String text = "text";

        String hash = auth.hashPassword(text);

        assertTrue(auth.checkPassword(text, hash));
    }

    @Test
    public void checkPasswordShouldReturnFalseOnDifferentText() {
        String text = "text";

        String hash = auth.hashPassword(text);

        assertFalse(auth.checkPassword("otherText", hash));
    }
}
