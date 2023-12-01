package com.vacinacerta.model.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersVaccinesViewModel {
    private String id;
    @JsonIgnore
    private UserViewModel userViewModel;
    private VaccineViewModel vaccineViewModel;
}
