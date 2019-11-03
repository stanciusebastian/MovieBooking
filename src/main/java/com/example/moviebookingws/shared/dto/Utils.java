package com.example.moviebookingws.shared.dto;

import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.security.SecureRandom;
import java.util.Random;

@Service
public class Utils {
    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String generateUserId(int length){
        return generateRandomString(length);
    }

    private String generateRandomString(int length){
        StringBuilder str = new StringBuilder(length);
        for(int i=0;i<length;++i){
            str.append(ALPHABET.charAt(RANDOM.nextInt(length)));
        }
        return new String(str);
    }

}
