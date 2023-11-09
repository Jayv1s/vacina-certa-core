package com.vacinacerta.usecase;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.gateway.interfaces.IVacinaCertaDbCommandGateway;
import com.vacinacerta.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Qualifier("CreateUser")
public class CreateUser implements IUseCase<UserContext, UserDTO> {

    @Autowired
    private final IVacinaCertaDbCommandGateway vacinaCertaDbCommandGatewayImpl;

    @Override
    public UserDTO execute(UserContext userContext) {
        UserDTO user = vacinaCertaDbCommandGatewayImpl.insertUser(userContext.getUserDTO());
        return user;
    }
}
