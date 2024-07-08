package ru.t1.summer.camp;

import ru.t1.summer.camp.candidate.CandidateController;
import ru.t1.summer.camp.candidate.CandidateControllerImpl;
import ru.t1.summer.camp.roles.RolesController;

import java.io.IOException;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        RolesController rolesController = new RolesController();
        try {
            List<String> roles = rolesController.getRoles();
            CandidateController candidateController = new CandidateControllerImpl();
            String lastName = "Surname";
            String firstName = "Name";
            String email = "name-surname@example.ru";
            String role = roles.get(1);
            System.out.println(roles);
            String signUp = candidateController.signUp(lastName, firstName, email, role);
            System.out.println(signUp);
            String code = candidateController.getCode(email);
            System.out.println(code);
            String status = candidateController.setStatus(email, code);
            System.out.println(status);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
