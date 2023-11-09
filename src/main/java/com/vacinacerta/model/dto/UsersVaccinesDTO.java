package com.vacinacerta.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersVaccinesDTO {
    private String id;
    @JsonProperty("user")
    private UserDTO userDTO;
    @JsonProperty("vaccine")
    private VaccineDTO vaccineDTO;
}
