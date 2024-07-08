package ru.t1.summer.camp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.t1.summer.camp.client.T1Client;
import ru.t1.summer.camp.dto.RoleDto;
import ru.t1.summer.camp.dto.SetStatusDto;
import ru.t1.summer.camp.dto.SignUpDto;
import ru.t1.summer.camp.utils.Encoder;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final T1Client t1Client;

    @PostMapping("/create-note")
    public ResponseEntity<?> createNewNoteAndSetStatus(@RequestBody SignUpDto signUpDto) {
        String email = signUpDto.getEmail();
        t1Client.writeToListOfCandidates(signUpDto);
        ResponseEntity<String> responseCode = t1Client.getCode(signUpDto.getEmail());
        String code = Objects.requireNonNull(responseCode.getBody()).substring(1, responseCode.getBody().length() - 1);
        return t1Client.setStatusTotableOfCandidates(new SetStatusDto(Encoder.encode(email, code), "increased"));
    }

    @GetMapping("/get-roles")
    public RoleDto getRoles() {
        return t1Client.retrieveRoles();
    }
}
