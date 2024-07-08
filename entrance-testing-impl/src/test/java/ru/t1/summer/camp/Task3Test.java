package ru.t1.summer.camp;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
class Task3Test {

    private static final String EMAIL = "email";
    private static final String CODE = "code";


    @Test
    void testBase64FromLib() {

        String originalInput = EMAIL + ":" + CODE;

        String actual = Base64.getEncoder().encodeToString(originalInput.getBytes());

        assertNotNull(actual);
        log.info(actual);
    }

    @Test
    void testBase64SelfDeveloped() {
        char[] BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        String convertString = EMAIL + ":" + CODE;
        byte[] bytes = convertString.getBytes();
        StringBuilder encoded = new StringBuilder();

        int paddingCount = 0;
        for (int i = 0; i < bytes.length; i += 3) {
            int b = ((bytes[i] & 0xFF) << 16) & 0xFFFFFF;
            if (i + 1 < bytes.length) {
                b |= (bytes[i + 1] & 0xFF) << 8;
            } else {
                paddingCount++;
            }
            if (i + 2 < bytes.length) {
                b |= (bytes[i + 2] & 0xFF);
            } else {
                paddingCount++;
            }

            for (int j = 0; j < 4 - paddingCount; j++) {
                int c = (b & 0xFC0000) >> 18;
                encoded.append(BASE64_CHARS[c]);
                b <<= 6;
            }
        }

        for (int j = 0; j < paddingCount; j++) {
            encoded.append('=');
        }

        String actual = encoded.toString();

        assertNotNull(actual);
        log.info(actual);
    }

    @Test
    void testBase64SelfDeveloped2() {
        var charsAvailableInBase64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        StringBuilder encoded = new StringBuilder();

        byte[] inputToBytes = (EMAIL + ":" + CODE).getBytes();


        for (int i = 0; i < inputToBytes.length; i += 3) {
            int b = ((inputToBytes[i] & 0xFF) << 16) & 0xFFFFFF;

            if (i + 1 < inputToBytes.length) {
                b |= (inputToBytes[i + 1] & 0xFF) << 8;
            }

            if (i + 2 < inputToBytes.length) {
                b |= (inputToBytes[i + 2] & 0xFF);
            }


            encoded.append(charsAvailableInBase64[(b >> 18) & 0x3F]);
            encoded.append(charsAvailableInBase64[(b >> 12) & 0x3F]);

            encoded.append(i + 1 < inputToBytes.length ? charsAvailableInBase64[(b >> 6) & 0x3F] : '=');
            encoded.append(i + 2 < inputToBytes.length ? charsAvailableInBase64[b & 0x3F] : '=');
        }

        String actual = encoded.toString();

        assertNotNull(actual);
        log.info(actual);
    }

}
