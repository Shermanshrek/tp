package org.develop.util;


public class DeleteBearer {
    public static final String bearer = "Bearer ";

    public static String deleteBearer(String token) {
        if (token.startsWith(bearer)) {
            token = token.substring(bearer.length()); // Удаляем первые 7 символов ("Bearer ")
        }
        return token;
    }
}
