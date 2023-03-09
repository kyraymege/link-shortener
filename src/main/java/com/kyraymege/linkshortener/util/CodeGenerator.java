package com.kyraymege.linkshortener.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class CodeGenerator {
    private int codeLength=5;

    public String generateRandomString(){

        SecureRandom secureRandom = new SecureRandom();
        String generated = "";

        var letters = "abcdefghijklmnoprstuvyzqwx1234567890_-"
                .toUpperCase()
                .chars()
                .mapToObj(x->(char)x)
                .collect(Collectors.toList());

        Collections.shuffle(letters);

        for (int i=0;i<codeLength;i++){
            generated += letters.get(secureRandom.nextInt(letters.size()));
        }
        return generated;
    }
}
