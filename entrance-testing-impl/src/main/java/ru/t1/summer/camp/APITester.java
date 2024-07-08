package ru.t1.summer.camp;


import java.io.IOException;

public class APITester {
    public static void main(String[] args) {
        try {
            String email = Generator.generateEmail("email@example.ru");

            String roles = Requests.getRoles();
            System.out.println("Roles: " + roles);

            String signUpResponse = Requests.signUp("Тестовый", "Пользователь", email, "Разработчик Java");
            System.out.println("Sign up response: " + signUpResponse);

            String emailCoded = Requests.getCode(email).replace("\"", "");
            System.out.println(emailCoded);
            System.out.println("Email coded: " + emailCoded);

            String token = Generator.encodeEmailCode(email, emailCoded);
            String setStatusResponse = Requests.setStatus(token, "increased");
            System.out.println("Set status response: " + setStatusResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}