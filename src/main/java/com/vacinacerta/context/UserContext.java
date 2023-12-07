package com.vacinacerta.context;

import com.vacinacerta.model.dto.UserDTO;
import com.vacinacerta.model.request.UserVaccineRequest;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserContext {
    private UserDTO userDTO;
    private String userId;
    private String vaccineId;
    private String jwtToken;
    private String actualPassword;
    private String newPassword;
    private List<UserVaccineRequest> vaccines;
}
