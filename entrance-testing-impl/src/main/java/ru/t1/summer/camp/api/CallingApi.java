package ru.t1.summer.camp.api;

import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.stereotype.Component;

/**
 * Класс, который содержит методы для вызова API
 */
@Component
public class CallingApi {
    private static final  String URL = "http://193.19.100.32:7000";
    private final String GET_ROLES = "/api/get-roles";
    private final String SIGN_UP = "/api/sign-up";
    private final String GET_CODE = "/api/get-code";
    private final String SET_STATUS = "/api/set-status";

    /**
     * Метод, который возвращает список ролей
     * @return String
     */
    public String getRoles(){
        System.out.println("Метод getRoles");
        Response response = RestAssured
                .given()
                .get(URL + GET_ROLES)
                .andReturn();

        if (response.getStatusCode() == 200){
            return response.getBody().asString();
        }
        else {
            System.out.println("Ошибка при выполнении GET запроса getRoles: " + response.getStatusLine());
            return response.body().asString();
        }
    }

    /**
     * Запись в таблицу кандидатов
     * @param firstName - имя
     * @param lastName - фамилия
     * @param email - почта
     * @param role - роль
     */
    public void signUp(String firstName, String lastName, String email, String role){

        System.out.println("Метод signUp");
        RestAssured.config = RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(true));
        String body = "{\n" +
                "            \"last_name\": \"%s\",\n" +
                "            \"first_name\": \"%s\",\n" +
                "            \"email\": \"%s\",\n" +
                "            \"role\": \"%s\"\n" +
                "        }".formatted(lastName, firstName, email, role);

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(body)
                .post(URL + SIGN_UP)
                .andReturn();

        if (response.getStatusCode() == 307) {
            String redirectUrl = response.getHeader("Location");

            response = RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .post(redirectUrl);
            System.out.println("Тело ответа: " + response.getBody().asString());
        }

        else if (response.getStatusCode() == 201) {
            System.out.println("Тело ответа: " + response.getBody().asString());
        } else {
            System.out.println("Ошибка при создании пользователя: " + response.getStatusLine());
            System.out.println("Тело ответа: " + response.getBody().asString());
        }
    }

    /**
     * Метод позволяет получить свой код, сгенерированный при записи в таблицу кандидатов
     * @param email - почта
     * @return String
     */
    public String getCode(String email){
        System.out.println("Метод getCode");
        Response response = RestAssured
                .given()
                .queryParam("email", email)
                .get(URL + GET_CODE)
                .andReturn();

        if (response.getStatusCode() == 200){
            String code = response.getBody().asString();
            System.out.println("Получен код: " + code);
            return code;
        }
        else {
            System.out.println("Ошибка при получении кода:" + response.getStatusLine());
            System.out.println("Тело ответа: " + response.getBody().asString());
            return  response.getBody().asString();
        }
    }

    /**
     * Метод позволяет установить статус записи в таблицу кандидатов
     * @param token - code с функции convertToBase64
     * @param status - статус
     */
    public void setStatus(String token, String status){
        System.out.println("Метод setStatus");
        String body = " {\n" +
                "                \"token\": \"%s\",\n" +
                "                \"status\": \"%s\"\n" +
                "                }".formatted(token, status);
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(body)
                .post(URL + SET_STATUS)
                .andReturn();

        if (response.getStatusCode() == 307) {
            String redirectUrl = response.getHeader("Location");

            response = RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .post(redirectUrl);
            System.out.println("Тело ответа: " + response.getBody().asString());
        }

        else if (response.getStatusCode() == 201){
            System.out.println("Тело ответа: " + response.getBody().asString());
        }
        else{
            System.out.println("Тело ответа: " + response.getBody());
        }
    }



}
