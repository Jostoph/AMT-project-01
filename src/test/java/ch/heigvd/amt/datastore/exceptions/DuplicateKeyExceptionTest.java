package ch.heigvd.amt.datastore.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

public class DuplicateKeyExceptionTest {

    @Test
    public void DuplicateKeyExceptionShouldThrowWithMessage() {
        try {
            throw new DuplicateKeyException("error message");
        } catch (DuplicateKeyException e) {
            assertEquals("error message", e.getMessage());
        }
    }
}
