package com.vacinacerta.model.view;

import com.vacinacerta.model.dto.UserDTO;
import com.vacinacerta.model.dto.VaccineDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersVaccinesViewModel {
    private String id;
    private UserDTO userDTO;
    private VaccineDTO vaccineDTO;
}
