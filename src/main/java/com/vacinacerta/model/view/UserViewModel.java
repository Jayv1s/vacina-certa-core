package com.vacinacerta.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserViewModel {
    public String id;
    public String firstName;
    public String lastName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public LocalDateTime dateOfBirth;
    public String document;
    public String documentType;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public LocalDateTime createdAt;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public LocalDateTime updatedAt;
    private String phone;
    private String nickname;
    //private Set<UsersVaccines> vaccines;
}
