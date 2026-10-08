package com.gym.crm.util;

import com.gym.crm.domain.Trainee;
import com.gym.crm.domain.Trainer;

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

    public static String generateUniqueUsername(String baseUsername,
                                                Collection<Trainee> trainees,
                                                Collection<Trainer> trainers) {
        String candidate = baseUsername;
        int suffix = 0;
        while (usernameExists(candidate, trainees, trainers)) {
            suffix++;
            candidate = baseUsername + suffix;
        }
        return candidate;
    }

    public static boolean usernameExists(String username, Collection<Trainee> trainees, Collection<Trainer> trainers) {
        for (Trainee trainee : trainees) {
            if (username.equals(trainee.getUsername())) {
                return true;
            }
        }
        for (Trainer trainer : trainers) {
            if (username.equals(trainer.getUsername())) {
                return true;
            }
        }
        return false;
    }

}
