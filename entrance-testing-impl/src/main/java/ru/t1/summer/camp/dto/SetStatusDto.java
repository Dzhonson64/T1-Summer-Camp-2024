package ru.t1.summer.camp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SetStatusDto {

   private String token;
   private String status;
}
