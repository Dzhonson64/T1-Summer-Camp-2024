package ru.t1.summer.camp.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class Encoder {

    private Encoder() {

    }

    public static String encode(String email, String code) {
        String stringToEncode = email + ":" + code;
        return Base64.getEncoder().encodeToString(stringToEncode.getBytes(StandardCharsets.UTF_8));
    }
}
