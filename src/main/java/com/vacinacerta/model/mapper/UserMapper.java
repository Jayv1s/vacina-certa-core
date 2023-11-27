package com.vacinacerta.model.mapper;

import com.vacinacerta.model.dto.UserDTO;
import com.vacinacerta.model.view.UserViewModel;

public class UserMapper {
    public static UserDTO convertToUsersDTO(UserViewModel user) {
        return UserDTO.builder()
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .dateOfBirth(user.getDateOfBirth())
                .document(user.getDocument())
                .documentType(user.getDocumentType())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .updatedAt(user.getUpdatedAt())
                .phone(user.getPhone())
                .nickname(user.getNickname())
                .build();
    }

    public static UserViewModel convertToUserViewModel(UserDTO user) {
        return UserViewModel.builder()
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .dateOfBirth(user.getDateOfBirth())
                .document(user.getDocument())
                .documentType(user.getDocumentType())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .updatedAt(user.getUpdatedAt())
                .phone(user.getPhone())
                .nickname(user.getNickname())
                .build();
    }
}
