package com.vacinacerta.usecase.user;

import com.vacinacerta.context.UserContext;
import com.vacinacerta.gateway.interfaces.IVacinaCertaDbCommandGateway;
import com.vacinacerta.usecase.IUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Qualifier("CreateUser")
public class CreateUserUseCase implements IUseCase<UserContext, String> {

    @Autowired
    private final IVacinaCertaDbCommandGateway vacinaCertaDbCommandGatewayImpl;

    @Override
    public String execute(UserContext userContext) {
        String userId = vacinaCertaDbCommandGatewayImpl.insertUser(userContext.getUserDTO(), userContext.getJwtToken());
        return userId;
    }
}
