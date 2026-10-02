package com.gym.crm.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilTest {

    @Test
    void usernameGenerator_shouldBuildLowercaseUsername() {
        String username = Util.usernameGenerator("John", "Doe");

        assertEquals("john.doe", username);
    }

    @Test
    void passwordGenerator_shouldReturnRequestedLengthAndAllowedCharacters() {
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";

        StringBuilder password = Util.passwordGenerator(32);

        assertEquals(32, password.length());
        for (int i = 0; i < password.length(); i++) {
            char character = password.charAt(i);
            assertTrue(allowedCharacters.indexOf(character) >= 0,
                    "Character at index " + i + " is not allowed: " + character);
        }
    }
}
