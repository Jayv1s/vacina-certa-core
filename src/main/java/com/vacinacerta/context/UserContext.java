package com.vacinacerta.context;

import com.vacinacerta.model.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserContext {
    private UserDTO userDTO;
    private String userId;
    private String vaccineId;
    private List<String> vaccines;
    private String jwtToken;
}
