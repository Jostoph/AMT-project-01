package ch.heigvd.amt.datastore.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

public class KeyNotFoundExceptionTest {

    @Test
    public void KeyNotFoundExceptionShouldThrowWithMessage() {
        try {
            throw new KeyNotFoundException("error message");
        } catch (KeyNotFoundException e) {
            assertEquals("error message", e.getMessage());
        }
    }
}
