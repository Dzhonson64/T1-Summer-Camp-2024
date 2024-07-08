package ru.t1.summer.camp.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EncoderTests {

    @Test
    @DisplayName("Test encode functionality")
    public void givenEmailWithCode_whenEncode_thenEncodeToBase64() {
        //given
        String email = "test32@gmail.com";
        String code = "f5debeb3c613d5666efbffefae299ede";
        //when
        String actualEncodedResult = Encoder.encode(email, code);
        String expectedEncodedResult = "dGVzdDMyQGdtYWlsLmNvbTpmNWRlYmViM2M2MTNkNTY2NmVmYmZmZWZhZTI5OWVkZQ==";
        //then
        Assertions.assertEquals(expectedEncodedResult, actualEncodedResult);
    }
}
