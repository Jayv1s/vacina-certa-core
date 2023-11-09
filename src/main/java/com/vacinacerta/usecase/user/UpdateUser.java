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
@Qualifier("UpdateUser")
public class UpdateUser implements IUseCase<UserContext, Void> {

    @Autowired
    private final IVacinaCertaDbCommandGateway vacinaCertaDbCommandGatewayImpl;

    @Override
    public Void execute(UserContext userContext) {

        vacinaCertaDbCommandGatewayImpl.updateUser(userContext.getUserId(), userContext.getUserDTO());

        return null;
    }
}
