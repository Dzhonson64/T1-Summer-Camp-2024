package ru.t1.summer.camp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.t1.summer.camp.api.CallingApi;
import ru.t1.summer.camp.convertor.BaseTo64Service;

import java.util.Scanner;

/**
 * Главный класс, который последовательно вызывает все методы предоставленного API
 */
@SpringBootApplication
public class Task4_Calling_Api implements CommandLineRunner {

    @Autowired
    CallingApi testApi;

    @Autowired
    BaseTo64Service baseTo64Service;
    public static void main(String[] args) {
        SpringApplication.run(Task4_Calling_Api.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String roles = testApi.getRoles();
        System.out.println("Список ролей:" + roles);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя");
        String firstName = scanner.nextLine();
        System.out.println("Введите фамилию");
        String lastName = scanner.nextLine();
        System.out.println("Введите почту");
        String email = scanner.nextLine();
        System.out.println("Введите роль");
        String role = scanner.nextLine();

        testApi.signUp(firstName, lastName, email, role);
        String code = testApi.getCode(email);
        code = code.replace("\"", "");
        String codeToBase64 = baseTo64Service.convertToBase64(email, code);
        testApi.setStatus(codeToBase64, "increased");
    }

}
