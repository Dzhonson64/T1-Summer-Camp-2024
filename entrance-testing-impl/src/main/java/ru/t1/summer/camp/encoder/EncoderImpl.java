package ru.t1.summer.camp.encoder;

import java.util.Base64;

public class EncoderImpl implements Encoder {
    @Override
    public String encodeToken(String email, String code) {
        code = code.substring(1, code.length() - 1);
        String resultStr = email +  ":" + code;
        return Base64.getEncoder().encodeToString(resultStr.getBytes());
    }
}
