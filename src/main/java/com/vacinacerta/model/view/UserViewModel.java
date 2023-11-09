package com.vacinacerta.model.view;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserViewModel {
    public String id;
    public String firstName;
    public String lastName;
    public LocalDateTime dateOfBirth;
    public String document;
    public String documentType;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
    //private Set<UsersVaccines> vaccines;
}
