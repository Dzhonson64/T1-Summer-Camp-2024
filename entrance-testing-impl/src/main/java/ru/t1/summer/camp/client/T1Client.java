package ru.t1.summer.camp.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import ru.t1.summer.camp.dto.RoleDto;
import ru.t1.summer.camp.dto.SetStatusDto;
import ru.t1.summer.camp.dto.SignUpDto;

@Component
public interface T1Client {

    @GetExchange("/api/get-roles")
    RoleDto retrieveRoles();

    @PostExchange("/api/sign-up")
    void writeToListOfCandidates(@RequestBody SignUpDto signUpDto);

    @GetExchange("/api/get-code/")
    ResponseEntity<String> getCode(@RequestParam(name = "email") String email);

    @PostExchange("/api/set-status/")
    ResponseEntity<String> setStatusTotableOfCandidates(@RequestBody SetStatusDto setStatusDto);

}
