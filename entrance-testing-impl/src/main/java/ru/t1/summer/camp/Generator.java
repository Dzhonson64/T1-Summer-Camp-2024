package ru.t1.summer.camp;
import java.util.Base64;

public class Generator {
    public static String generateEmail(String email) {
        return email;
    }

    public static String encodeEmailCode(String email, String emailCoded) {
        String combinedString = email + ":" + emailCoded;
        byte[] combinedBytes = combinedString.getBytes();
        String base64String = Base64.getEncoder().encodeToString(combinedBytes);
        return base64String;
    }
}