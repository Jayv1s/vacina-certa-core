package com.vacinacerta.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersVaccinesDTO {
    private String id;
    private UserDTO userDTO;
    private VaccineDTO vaccineDTO;
    private Date appliedAt;
}
