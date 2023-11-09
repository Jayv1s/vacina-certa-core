package com.vacinacerta.model.mapper;

import com.vacinacerta.model.dto.UsersVaccinesDTO;
import com.vacinacerta.model.view.UsersVaccinesViewModel;

public class UsersVaccinesMapper {

    public static UsersVaccinesDTO convertToUsersVaccinesDTO(UsersVaccinesViewModel usersVaccinesViewModel) {
        return UsersVaccinesDTO.builder()
                .id(usersVaccinesViewModel.getId())
                .userDTO(usersVaccinesViewModel.getUserDTO())
                .vaccineDTO(usersVaccinesViewModel.getVaccineDTO())
                .build();
    }

    public static UsersVaccinesViewModel convertToUsersVaccinesViewModel(UsersVaccinesDTO usersVaccinesDTO) {
        return UsersVaccinesViewModel.builder()
                .id(usersVaccinesDTO.getId())
                .userDTO(usersVaccinesDTO.getUserDTO())
                .vaccineDTO(usersVaccinesDTO.getVaccineDTO())
                .build();
    }
}
