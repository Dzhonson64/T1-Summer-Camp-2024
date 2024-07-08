package ru.t1.summer.camp.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import ru.t1.summer.camp.dataUtil.DataUtil;

import java.util.List;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class T1ClientTests {
    private static WireMockServer wireMockServer;
    private final T1Client underTest;

    @Autowired
    public T1ClientTests(T1Client t1Client) {
        this.underTest = t1Client;
    }

    private static final String ALL_ROLES_RESPONSE = "{\n" +
            "        \"roles\": [\n" +
            "        \"Системный аналитик\",\n" +
            "                \"Разработчик Java\",\n" +
            "                \"Разработчик JS/React\",\n" +
            "                \"Тестировщик\",\n" +
            "                \"Прикладной администратор\"\n" +
            "            ]\n" +
            "    }";

    @BeforeAll
    public static void beforeAll() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
    }

    @AfterAll
    public static void afterAll() {
        wireMockServer.stop();
    }

    @Test
    @DisplayName("Test get all roles functionality")
    public void givenClient_whenRequestRoles_thenGetAllRoles() {
        //given
        stubFor(get("/api/get-roles")
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json; charset=utf8")
                        .withBody(ALL_ROLES_RESPONSE)
                ));
        //when
        List<String> actualListOfRoles = underTest.retrieveRoles().getRoles();
        //then
        assertNotNull(actualListOfRoles);
        assertEquals(5, actualListOfRoles.size());
        assertThat(actualListOfRoles)
                .containsExactly("Системный аналитик", "Разработчик Java", "Разработчик JS/React", "Тестировщик", "Прикладной администратор");
    }

    @Test
    @DisplayName("Test get code functionality")
    public void givenEmail_whenGetCode_thenReturnCode() {
        //given
        stubFor(get("/api/get-code")
                .willReturn(
                        ok("f5debeb3c613d5666efbffefae299ede")
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                )
        );
        //when
        var responseCode = underTest.getCode("test32@gmail.com");
        String actualCode = Objects.requireNonNull(responseCode.getBody()).substring(1, responseCode.getBody().length() - 1);
        //then
        assertEquals(actualCode, "f5debeb3c613d5666efbffefae299ede");
    }

    @Test
    @DisplayName("Test set status functionality")
    public void givenEmailAndEncodedCode_whenSetStatus_thenReturnSuccessfulResponse() {
        //given
        stubFor(post("/api/set-status")
                .willReturn(
                        ok("Статус increased зафиксирован. Задание выполнено")
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                )
        );
        //when
        var response = underTest.setStatusTotableOfCandidates(DataUtil.setStatusDto());
        String stringResponse = Objects.requireNonNull(response.getBody()).substring(1, response.getBody().length() - 1);
        //then
        assertEquals(stringResponse, "Статус increased зафиксирован. Задание выполнено");
    }
}
