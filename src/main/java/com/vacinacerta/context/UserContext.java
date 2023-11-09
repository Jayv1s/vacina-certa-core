package com.vacinacerta.context;

import com.vacinacerta.model.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserContext {
    private UserDTO userDTO;
    private String userId;
}
