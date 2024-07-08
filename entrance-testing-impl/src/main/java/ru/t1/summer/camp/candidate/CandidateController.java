package ru.t1.summer.camp.candidate;

public interface CandidateController {

    String signUp(String lastName, String firstName, String email, String role);

    String getCode(String email);

    String setStatus(String email, String code);
}
