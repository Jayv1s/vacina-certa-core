package com.vacinacerta.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    public String id;
    public String firstName;
    public String lastName;
    public LocalDateTime dateOfBirth;
    public String document;
    public String documentType;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
    //TODO: adicionar e-mail
    private Set<Object> vaccines;
}
