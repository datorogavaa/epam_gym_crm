package com.gym.crm.util;

import com.gym.crm.domain.User;

import java.util.Collection;
import java.util.Random;

public class Util {

    private static final Random RANDOM = new Random();

    private Util() {
    }

    public static String usernameGenerator(String firstName, String lastName) {
        return firstName + "." + lastName;
    }

    public static String passwordGenerator(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(RANDOM.nextInt(chars.length())));
        }
        return password.toString();
    }

    @SafeVarargs
    public static String generateUniqueUsername(String baseUsername, Collection<? extends User>... userCollections) {
        String candidate = baseUsername;
        int suffix = 0;
        while (usernameExists(candidate, userCollections)) {
            suffix++;
            candidate = baseUsername + suffix;
        }
        return candidate;
    }

    @SafeVarargs
    public static boolean usernameExists(String username, Collection<? extends User>... userCollections) {
        if (userCollections == null) {
            return false;
        }
        for (Collection<? extends User> users : userCollections) {
            if (users != null) {
                for (User user : users) {
                    if (user != null && username.equals(user.getUsername())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}