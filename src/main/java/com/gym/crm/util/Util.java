package com.gym.crm.util;

import java.util.Random;

public class Util {

    private Random random = new Random();

    public static String usernameGenerator(String firstName, String lastName) {
        String username = firstName.toLowerCase() + "." + lastName.toLowerCase();
        return username;
    }

    public static StringBuilder passwordGenerator(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password;
    }

}
