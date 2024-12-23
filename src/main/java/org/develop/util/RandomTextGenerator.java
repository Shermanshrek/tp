package org.develop.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RandomTextGenerator {
    private int textLength;
    private String[] allowedSymbols;

    public String generateRandomText() {
        StringBuilder result = new StringBuilder(textLength);
        Random random = new Random();
        for (int i = 0; i < textLength; i++) {
            int randomIndex = random.nextInt(allowedSymbols.length);
            result.append(allowedSymbols[randomIndex]);
        }
        return result.toString();
    }
}
