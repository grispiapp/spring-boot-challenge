package com.example.demo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

@Component
public class UserGenerator implements CommandLineRunner {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String DOMAIN = "@example.com";
    private static final int NAME_LENGTH = 8; // Length of the random name
    private static final int EMAIL_PREFIX_LENGTH = 10; // Length of the random email prefix

    private final UserRepository userRepository;

    UserGenerator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void generateUniqueUsers(int count) {
        Set<String> uniqueEmails = new HashSet<>(count);
        Random random = new Random();
        System.out.println("Generating " + count + " unique users");
        IntStream.range(0, count).forEach(i -> {
            User user = new User();
            String name = generateRandomString(random, NAME_LENGTH);
            user.setName(name);

            String email;
            do {
                email = generateRandomString(random, EMAIL_PREFIX_LENGTH) + DOMAIN;
            } while (!uniqueEmails.add(email)); // Ensure email is unique

            user.setEmail(email);

            userRepository.save(user);
            if (i % 100 == 0) {
                System.out.print(".");
            }
        });
        System.out.println("Done.");
    }

    private String generateRandomString(Random random, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsById(1L)) {
            generateUniqueUsers(1000);
        }
    }
}
