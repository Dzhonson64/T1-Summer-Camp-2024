package ru.t1.summer.camp.convertor;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class BaseTo64Service {

    /**
     * @param email - почта
     * @param code - код полученный с метода getCode
     * @return обработанный код
     */
    public String convertToBase64(String email, String code) {
        char[] BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        String convertString = email + ":" + code;
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

        return encoded.toString();
    }
}
