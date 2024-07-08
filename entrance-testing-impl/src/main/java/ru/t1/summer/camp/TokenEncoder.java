package ru.t1.summer.camp;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TokenEncoder {
    public String encodeToken(String email, String code) {
        byte[] rawBytes = (email + ":" + code)
                .getBytes(StandardCharsets.UTF_8);

        byte[] encodedBytes = Base64.getEncoder().encode(rawBytes);

        return new String(encodedBytes, StandardCharsets.US_ASCII);
    }
}
