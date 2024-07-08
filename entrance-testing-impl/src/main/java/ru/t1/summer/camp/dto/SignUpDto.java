package ru.t1.summer.camp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class SignUpDto {

    @JsonProperty("last_name")
    private String last_name;

    @JsonProperty("first_name")
    private String first_name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("role")
    private String role;

}
