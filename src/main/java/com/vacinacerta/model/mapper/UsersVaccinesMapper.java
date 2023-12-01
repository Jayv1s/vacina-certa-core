package com.vacinacerta.model.mapper;

import com.vacinacerta.model.dto.UsersVaccinesDTO;
import com.vacinacerta.model.view.UsersVaccinesViewModel;

import java.util.Objects;

public class UsersVaccinesMapper {

    public static UsersVaccinesDTO convertToUsersVaccinesDTO(UsersVaccinesViewModel usersVaccinesViewModel) {
        return UsersVaccinesDTO.builder()
                .id(usersVaccinesViewModel.getId())
                .userDTO(UserMapper.convertToUsersDTO(usersVaccinesViewModel.getUserViewModel()))
                .vaccineDTO(VaccineMapper.convertToVaccineDTO(usersVaccinesViewModel.getVaccineViewModel()))
                .build();
    }

    public static UsersVaccinesViewModel convertToUsersVaccinesViewModel(UsersVaccinesDTO usersVaccinesDTO) {
        return UsersVaccinesViewModel.builder()
                .id(usersVaccinesDTO.getId())
                .userViewModel(Objects.nonNull(usersVaccinesDTO.getUserDTO()) ? UserMapper.convertToUserViewModel(usersVaccinesDTO.getUserDTO()) : null)
                .vaccineViewModel(Objects.nonNull(usersVaccinesDTO.getVaccineDTO()) ? VaccineMapper.convertToVaccineViewModel(usersVaccinesDTO.getVaccineDTO()) : null)
                .build();
    }
}
