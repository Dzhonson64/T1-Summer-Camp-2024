package ru.t1.summer.camp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class RoleDto {
    @JsonProperty("roles")
    List<String> roles;
}
